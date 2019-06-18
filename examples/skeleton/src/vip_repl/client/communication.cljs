(ns vip-repl.client.communication
  (:require
    [concept1.client.ainit]
    [concept1.client.model :as model]
    [concept1.client.config :as config]
    [taoensso.sente :as sente]))

(defn get-chsk-url
  "Connect to a configured server instead of the page host"
  [protocol chsk-host chsk-path type]
  (let [protocol (case type :ajax protocol
                            :ws   (if (= protocol "https:") "wss:" "ws:"))]
    (str protocol "//" config/server chsk-path)))

(defonce channel-socket
  (with-redefs [sente/get-chsk-url get-chsk-url]
    (sente/make-channel-socket! "/chsk" {:type :auto})))
(defonce chsk (:chsk channel-socket))
(defonce ch-chsk (:ch-recv channel-socket))
(defonce chsk-send! (:send-fn channel-socket))
(defonce chsk-state (:state channel-socket))

(defn add-point! [point]
  (chsk-send! [:concept1/add-point point]))

(defmulti event-msg-handler :id)

(defmethod event-msg-handler :default [{:as ev-msg :keys [event]}]
  (println "Unhandled event: %s" event))

(defmethod event-msg-handler :chsk/state [{:as ev-msg :keys [?data]}]
  (if (= ?data {:first-open? true})
    (println "Channel socket successfully established!")
    (println "Channel socket state change:" ?data)))

(defmethod event-msg-handler :chsk/recv [{:as ev-msg :keys [?data]}]
  (model/world! (second ?data)))

(defmethod event-msg-handler :chsk/handshake [{:as ev-msg :keys [?data]}]
  (let [[?uid ?csrf-token ?handshake-data] ?data]
    (println "Handshake:" ?data)
    (model/uid! ?uid)))

(defonce router
  (sente/start-client-chsk-router! ch-chsk event-msg-handler))

(defn respawn []
  (chsk-send! [:concept1.client/respawn]))

