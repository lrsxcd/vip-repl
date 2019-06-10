(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["react-leaflet" :refer [Map TileLayer]]))

(defn app
  []
  [:div "VIP-REPL"]
  
  [:> Map  {:center [ 51.505 -0.09 ]
            :zoom 13} [:> TileLayer
                       {:attribution= "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
                        :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
