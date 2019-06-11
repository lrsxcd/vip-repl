; (ns app.vip-repl-api
;   (:require [re-frame.core :as rf]
;             ["react-leaflet" :refer [Map TileLayer  Marker Popup]]))

; (defn create-markers
;   [markers]
;   (for [m markers]
;   [:> Marker {:position m}]))

; (def create-map [center zoom markers]
;   [:> Map  {:center center :zoom zoom}
;    [:> TileLayer  {:attribution= "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
;                    :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]
;    [create-markers markers]])





