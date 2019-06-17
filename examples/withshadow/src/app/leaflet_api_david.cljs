(ns app.leaflet-api-david
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [react-leaflet :refer [Map TileLayer  Marker Popup]]
            ;; the following two are required for gstring/format
            [goog.string :as gstring]
            [goog.string.format]))

(def marker-data [{:lat 59.92 :lng 10.75}
                  {:lat 59.91 :lng 10.74}
                  {:lat 59.91 :lng 10.76}])

(defn create-marker
  [lat lng]
  [:>  Marker {:position [lat, lng]} 
   [:> Popup (gstring/format "I am a marker at (%.2f, %.2f)" lat lng)]])

(defn create-markers
  "Create markers HTML snippet

  Arguments:
      data {list of maps} -- list of marker
      lat_key {str} -- key for latitude
      lng_key {str} -- key for longitude
  "
  ([data] (create-markers data :lat :lng))
  ([data lat_key lng_key]
   (transduce (comp
               (filter #(and (% lat_key) (% lng_key)))
               (map #(create-marker (% lat_key) (% lng_key))))
              conj data)))

(defn create-map
  "Create  react-leaflet map

  Arguments:
      center_lat {float} -- map center latitude
      center_lng {float} -- map center logntitude
      zoom {int} -- zoom in number between 1 and 30
      markers {string} -- markers that are created with create_markers
      filters {string} -- filters that are created with create_filters 
  
  Returns:
      string  -- html snippet of a react-leaflet string
  "
  [center_lat center_lng zoom markers filters]

  (into
   [:> Map {:center [center_lat center_lng] :zoom zoom}
    [:> TileLayer {:attribution=
                   (str "&copy; <a href=&quot;http://osm.org/copyright&quot;>"
                        "OpenStreetMap</a> contributors")
                   :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]]
   (create-markers marker-data)))


