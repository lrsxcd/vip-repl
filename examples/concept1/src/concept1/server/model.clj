(ns concept1.server.model
  (:require
    [clojure.core.memoize :as memo]
    [clojure.set :as set]))

(defonce state
  (atom {}))

(defn assoc-in! [path new-val]
  (swap! state assoc-in path new-val))

(defn next-uid [{:keys [params]}]
  (java.util.UUID/randomUUID))


(comment

  (reset!
   state
   {:components [[:leaflet []]]})

  )
