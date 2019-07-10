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
   [react-leaflet :refer [Map TileLayer Marker Circle CircleMarker Popup Tooltip]]
   [clojure.walk :as walk]
   [cljs.pprint :refer [pprint]]
   [concept1.client.vega :as vega :refer [vega-lite]]
   ;; the following two are required for gstring/format
   [goog.string :as gstring]
   [goog.string.format]
   )
  (:import
   [goog.crypt Md5]))

(defn tile-layer []
  [:> TileLayer {:attribution=
                 (str "Tiles &copy ; Esri &mdash; Source: Esri, i-cubed, USDA, "
                      "USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, "
                      "UPR-EGP, and the GIS User Community")
                 :url
                 (str "https://server.arcgisonline.com/ArcGIS/rest/services/"
                      "World_Imagery/MapServer/tile/{z}/{y}/{x}")}])


(defn tile-layer-osm
  []
  [:> TileLayer {:attribution=
                   (str "&copy; <a href=&quot;http://osm.org/copyright&quot;>"
                        "OpenStreetMap</a> contributors")
                   :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}])

(defn leaflet
  "Reagent component that renders a leaflet map"
  [data-state {:keys [center
                      zoom
                      circle-markers
                      selection-path]}]
  [:div
   (->> (into
         [:> Map  {:center  center
                   :zoom    zoom
                   :onclick (fn [this]
                              (->> this
                                   .-latlng
                                   ((juxt #(.-lat %) #(.-lng %)))
                                   (communication/assoc-in! selection-path)))}
          [tile-layer-osm]]
         (->> circle-markers
              (map (fn [{:keys [data-path LABEL LATLNG]
                         :or   {LABEL :label LATLNG :latlng}}]
                     (->> data-path
                          (get-in data-state)
                          (map (fn [point]
                                 [:> CircleMarker {:center (LATLNG point)}
                                  [:> Tooltip (LABEL point)]])))))
              (apply concat))))])

(defn string
  "Reagent component that renders a string"
  [data-state {:keys [data-path fmt-txt]}]
  (let [value (str (get-in data-state data-path))]
    (gstring/format fmt-txt value)))


(def component-lookup
  {:leaflet leaflet
   :string  string
   :vega-lite vega-lite})

(defn first-when-vector [v]
  (when (vector? v)
    (first v)))

(defn walk-lookup [data-state form]
 "Reconstruct the reagent components by inserting 'data-state' as the
  first parametr in every subform which is either :leaflet or :string"
   (->> form
       (walk/postwalk
        (fn [subform]
          (if-let [component (-> subform
                                 first-when-vector
                                 component-lookup)]
            (if (= component vega-lite)
              (into [component] (rest subform))
              (into [component data-state] (rest subform)))
            subform)))))

(defn main []
  (let [state (:world @model/app-state)]
    [:div#mainvis
     (->> state
          :components
          (map (partial walk-lookup (:data state)))
          (into [:div#compcontainer]))]))
