(ns concept1.server.main
  (:require
    [org.httpkit.server :as server]
    [concept1.server.routes :as routes]
    [environ.core :as environ])
  (:gen-class))

(def port 3000)

(defn -main [& args]
  (println "Server starting...")
  (routes/start-websocket)
  (routes/start-router)
  (routes/start-ticker)
  (server/run-server #'routes/handler
                     {:port (or (some-> (first args) (Integer/parseInt))
                                (environ/env :http-port port))})
  (println "Ready at port" port "."))
