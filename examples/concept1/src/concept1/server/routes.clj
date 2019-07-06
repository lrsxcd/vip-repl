(ns concept1.server.routes
  (:require
   [concept1.server.model :as model]
   [ring.middleware.defaults :as defaults]
   [ring.middleware.reload :as reload]
   [ring.middleware.cors :as cors]
   [ring.util.response :as response]
   [environ.core :as environ]
   [taoensso.sente :as sente]
   [taoensso.sente.server-adapters.http-kit :as http-kit]
   [compojure.core :refer [defroutes GET POST]]
   [compojure.route :as route]
   [clojure.pprint :refer [pprint]]))

(declare channel-socket)

(defn start-websocket []
  (defonce channel-socket
    (sente/make-channel-socket!
      http-kit/sente-web-server-adapter
      {:user-id-fn #'model/next-uid})))

(defroutes routes
  (GET "/" req (response/content-type
                 (response/resource-response "public/index.html")
                 "text/html"))
  (GET "/status" req (str "Running: " (pr-str @(:connected-uids channel-socket))))
  (GET "/chsk" req ((:ajax-get-or-ws-handshake-fn channel-socket) req))
  (POST "/chsk" req ((:ajax-post-fn channel-socket) req))
  (route/resources "/")
  (route/not-found "Nnt found"))

(def handler
  (-> #'routes
    (cond-> (environ/env :dev?) (reload/wrap-reload))
    (defaults/wrap-defaults (assoc-in defaults/site-defaults [:security :anti-forgery] false))
    (cors/wrap-cors :access-control-allow-origin [#".*"]
                    :access-control-allow-methods [:get :put :post :delete]
                    :access-control-allow-credentials ["true"])))

(defmulti event :id)

(defmethod event :default [{:as ev-msg :keys [event]}]
  (println "Unhandled event: " event))

(defmethod event :chsk/uidport-open [{:keys [uid client-id]}]
  (println "New connection:" uid client-id))

(defmethod event :chsk/uidport-close [{:keys [uid]}]
  (println "Disconnected:" uid))

(defmethod event :chsk/ws-ping [_])

(defmethod event :concept1/assoc-in!
  [ev-msg]
  (let [[_ [path new-val]] (:event ev-msg)]
    (model/assoc-in! path new-val)))

(defn start-router []
  (defonce router
    (sente/start-chsk-router! (:ch-recv channel-socket) event)))

(defn broadcast []
  (doseq [uid (:any @(:connected-uids channel-socket))]
    ((:send-fn channel-socket) uid [:chsk/state @model/state])))

(defn ticker []
  (while true
    (Thread/sleep 150)
    (model/compute-reactions!)
    (try
      (broadcast)
      (catch Exception ex
        (println ex)))))

(defn start-ticker []
  (defonce ticker-thread
    (doto (Thread. ticker)
      (.start))))
