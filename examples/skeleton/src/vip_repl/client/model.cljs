(ns concept1.client.model
  (:require
    [reagent.core :as reagent]))

(defonce app-state
  (reagent/atom
   {:world {:points [[50 0]]}}))

(defn world! [world]
  (swap! app-state assoc :world world))

(defn uid! [uid]
  (swap! app-state assoc :uid uid))
