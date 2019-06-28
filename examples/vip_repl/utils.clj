(ns vip-repl.utils)
(defn ->context [{:keys [name]}])

(defn default-context []
  "if no context exists create one and return it. If an existing context exists returnes it if we have more than one context - return the last created ")

(defn start-ui-server [{:keys [context]}]
  "Starts the ui server")
(defn start-data-server [{:keys [context]}]
   "Starts the websocket server so we sync data between clients and api")

(defn ->event [{:keys [event-id client-callback server-callback]}]
  "Adds events to any vip-repl visual element")





