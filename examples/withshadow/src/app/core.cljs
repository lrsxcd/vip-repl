(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ; ["@react-leaflet" :refer [Map]]
            ))

(defn app
  []
  [:div "VIP-REPL"]
  ; [:> Map])
)

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
