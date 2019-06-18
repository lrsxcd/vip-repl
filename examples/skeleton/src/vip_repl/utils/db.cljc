(ns vip-repl.utils.db)

(def db-history atom [])

(def db atom {:data {}
              :clients {}})

(defn client-message "" [client-id, data]
  (swap! db assoc-in [:clients client-id] client))

  



