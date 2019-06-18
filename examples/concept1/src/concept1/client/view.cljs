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
    [react-leaflet :refer [Map TileLayer Marker Circle Popup]])
  (:import
   [goog.crypt Md5]))

(defn leaflet-map [circles
                   markers
                   selection
                   state]
  [:div {:height "400px"}
   (->> 
        (into
         [:> Map  {:center  [51.505 -0.09]
                   :zoom    4
                   ;; :onclick (fn [this]
                   ;;            (-> this
                   ;;                .-latlng
                   ;;                ((juxt #(.-lat %) #(.-lng %)))
                   ;;                communication/add-point!))
                   }
          [:> TileLayer  {:attribution= "Tiles &copy ; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community"
                          :url          "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}"}]]

         (concat
          (->> circles
               (get-in state)
               (map (fn [{:keys [latlng label]}]
                      [:> Circle {:position latlng}
                       [:> Popup label]])))
          (->> markers
               (get-in state)
               (map (fn [{:keys [latlng label]}]
                      [:> Marker {:position latlng}
                       [:> Popup label]]))))))])

(def lookup
  {:leaflet-map leaflet-map})

(defn main [state]
  [:div
   [:p (pr-str state)]
   (->> state
        :world
        :components
        (map (fn [comp-type & args]
               (println [comp-type args])
               (->> args
                    vec
                    (conj state)
                    (into [(lookup comp-type)]))))
        (into [:div]))])

