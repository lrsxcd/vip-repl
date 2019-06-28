(ns concept1.client.view
  (:require
    [clojure.string :as string]
    [goog.events :as events]
    [goog.events.KeyCodes :as KeyCodes]
    [concept1.client.model :as model]
    [concept1.client.communication :as communication]
    [goog.crypt :as crypt]
    [goog.dom.forms :as forms]
    [reagent.core :as reagent :refer [atom]]
    [react-leaflet :refer [Map TileLayer  Marker Popup]])
  (:import
   [goog.crypt Md5]))

(defn leaflet-map [state]
  [:div {:height "400px"}
   (->> state
        :world
        :points
        (map (fn [point]
               [:> Marker {:position point}
                [:> Popup "A pretty CSS3 popup."]]))
        (into
         [:> Map  {:center  [51.505 -0.09]
                   :zoom    4
                   :onclick (fn [this]
                              (-> this
                                  .-latlng
                                  ((juxt #(.-lat %) #(.-lng %)))
                                  communication/add-point!))}
          [:> TileLayer  {:attribution= "Tiles &copy ; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community"
                          :url          "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}"}]]))])

(defn main []
  [:div
   [leaflet-map @model/app-state]])

