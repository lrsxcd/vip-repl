(ns vip-repl.utils.db)

(def db atom {:data {}
              :clients {}})



(defn add-client [client-id client]
  (swap! db assoc-in [:clients client-id] client))
  



