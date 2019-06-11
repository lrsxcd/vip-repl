(ns app.leaflet-api-david
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["react-leaflet" :refer [Map TileLayer  Marker Popup]]))

(defn create-map
  "Create HTML snippet for react-leaflet map

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
  [:> Map  {:center [center_lat center_lng] :zoom zoom}
   [:> TileLayer  {:attribution= "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
                   :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]
   ])
   

