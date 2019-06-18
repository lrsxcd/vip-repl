(ns concept1.server.model
  (:require
    [clojure.core.memoize :as memo]
    [clojure.set :as set]))

(defonce state
  (atom {:points []}))

(defn add-point! [point]
  (swap! state update :points #(conj % point)))

(defn next-uid [{:keys [params]}]
  (java.util.UUID/randomUUID))
