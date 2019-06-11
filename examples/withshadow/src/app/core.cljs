(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ; [app.vip-repl-api :refer [create-map]]
            ["react-leaflet" :refer [Map TileLayer  Marker Popup]]))


(defn app
  []
  [:div "VIP-REPL"]

  [:> Map  {:center [51.505 -0.09]
            :zoom 13}
   [:> TileLayer  {:attribution= "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
                   :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]
   [:> Marker {:position [51.505 -0.09]}
    [:> Popup "A pretty CSS3 popup."]]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
