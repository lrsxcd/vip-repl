(ns concept1.server.main
  (:require
    [org.httpkit.server :as server]
    [concept1.server.routes :as routes]
    [environ.core :as environ])
  (:gen-class))

(def default-port 3000)

(defn start-server! [& {:keys [port]
                        :or   {port default-port}}]
  (println "Server starting...")
  (routes/start-websocket)
  (routes/start-router)
  (routes/start-ticker)
  (server/run-server #'routes/handler
                     {:port port})
  (println "Ready at port" port "."))

(defn -main [& args]
  (start-server! {:port (or (some-> (first args) (Integer/parseInt))
                            (environ/env :http-port default-port))}))

