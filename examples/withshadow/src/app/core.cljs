(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ; [app.vip-repl-api :refer [create-map]]
            [app.leaflet-api-david :refer [create-map]]
            [react-leaflet :refer [Map TileLayer  Marker Popup]]))

; (defn input-with-change-event [id type value]
;   [:> Input {:control true
;              :id id
;              :type type
;              :value value}]
;   )


(defn app
  []
  [:div
  [create-map 59.92 10.75 12 nil nil]
  ; [create-map 59.92 10.75 12 nil nil]
  [:div {:id "filters-container"} [:input {:type "range" :id "start"
                                           :name "test" :min "0" :max "11"}]
   [:input {:value "4"}]]])

;;  [create-map  51.505 -0.09  13 "" ""]


  ; [:> Map  {:center [51.505 -0.09]
  ;           :zoom 13}
  ;  [:> TileLayer  {:attribution= "&copy; <a href=&quot;http://osm.org/copyright&quot;>OpenStreetMap</a> contributors"
  ;                  :url "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"}]
  ;  [:> Marker {:position [51.505 -0.09]}
  ;   [:> Popup "A pretty CSS3 popup."]]])

(defn ^:dev/after-load start
  []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export init
  []
  (start))
