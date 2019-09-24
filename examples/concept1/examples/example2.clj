(ns example1
  (:require [concept1.server.main :refer [start-server!]]
            [concept1.server.model :refer [clear! add-component! add-dataset!
                                           compute-reactions! state
                                           clear-components!]]
            [concept1.server.plumbing :refer [add-reaction!]]
            [concept1.server.components :refer [make-text-comp
                                                make-map-comp
                                                make-comp-container
                                                append-comp-container]]
            [concept1.server.util :refer [haversine-distance]]))

(comment
  (start-server!))

(comment
  (clear!))

(comment
  (clear-components!))

(def my-places
  [{:name   "SweetDream Guesthouse"
    :latlng [60.1863315,24.9620961]}
   {:name   "Ravintola Soi Soi"
    :latlng [60.1884942,24.9598521]}
   {:name   "OHMYGOODNESS.fi"
    :latlng [60.188258,24.9562313]}
   {:name   "Helsinki Central Station"
    :latlng [60.1729912,24.9468435]}
   {:name   "Hotel St. George Helsinki"
    :latlng [60.1668387,24.9428342]}
   {:name   "Emblica Oy"
    :latlng [60.1708757,24.9483542]}
   {:name   "Eventlokal Telakka"
    :latlng [60.1523056,24.9270696]}])

(add-dataset! :places my-places)

(add-reaction! n-places [places]
               (count places))

@state

(add-reaction! closest-place [places selected-location]
               (apply min-key (fn [place]
                                (-> place
                                    :latlng
                                    (haversine-distance selected-location)))
                      places))

@state

(def map1 (make-map-comp :center [60.1729912,24.9468435] :zoom 12
                         :circle-markers [{:data-path [:places]
                                           :LABEL :name}]
                         :selection-path [:selected-location]))

(def map1-txt1 (make-text-comp "Location selected: %s"
                               [:selected-location]))

(def cont1 (make-comp-container map1 map1-txt1))

(add-component! cont1)

(def map1-txt2 (make-text-comp "Closest place: %s"
                               [:closest-place :name]))

(add-component! (append-comp-container cont1 map1-txt2))

