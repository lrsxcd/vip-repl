(ns app.vip-repl-api
  (:require [re-frame.core :as rf]
            ["react-leaflet" :refer [Map TileLayer  Marker Popup]]))

(def create-map [centre zoom markers])
