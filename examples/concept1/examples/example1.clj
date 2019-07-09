(ns example1
  (:require [concept1.server.main :refer [start-server!]]
            [concept1.server.model :refer [clear! add-component! add-dataset! compute-reactions! state]]
            [concept1.server.plumbing :refer [add-reaction!]]
            [fastmath.core :refer [cos asin sqrt]]))

(start-server!)

;; Now, you can start a client with `npm yarn watch`, and open `localhost:8700` in the browser.

;; At any moment, you can clear all data, reactions and components with this command:
(comment
  (clear!))

;; Add a dataset
(add-dataset! :cities [{:name       "Milan"
                        :population 1156903
                        :latlng     [45.466667 9.2]}
                       {:name       "Warsaw"
                        :population 165167
                        :latlng     [52.25 21]}
                       {:name "Oslo"
                        :population 123000
                        :latlng [59.2 10.3]}])

;; Add a reaction
(add-reaction! n-cities [cities]
               (count cities))

;; At any stage, you can check the state of the system:
@state

;; Add a visual components
(add-component! [:h3 [:string {:data-path [:n-cities]}]
                 " cities"])

(add-component! [:div
                 [:leaflet {:center         [45 10]
                            :zoom           3
                            :circle-markers
                            [{:data-path [:cities]
                              :LABEL     :name}]
                            :selection-path [:selected-location]}]])

(add-component! [:div
                 [:h3
                  "Selected location: "
                  [:string {:data-path [:selected-location]}]]])

;; Click the map in various places, and see the components respond.

;; Let us show the closest city to the selected location:

(defn haversine-distance
  "Compute the distance between two points given in lat-lng coordinates,
  using the haversine formula.
  https://en.wikipedia.org/wiki/Haversine_formula
  https://stackoverflow.com/a/21623206"
  [[lat1 lng1] [lat2 lng2]]
  (let [c (fn
            ;; cosine of an angle
            ;; given in degrees
            [alpha]
            (-> alpha
                (* Math/PI)
                (/ 180)
                cos))]
    (-> (+ 1
           (- (c (- lat2 lat1)))
           (* (c lat1)
              (c lat2)
              (- 1 (c (- lng2 lng1)))))
        (/ 2)
        sqrt
        asin
        (* 12742) ; Earth diameter
        )))


(add-reaction! closest-city [cities selected-location]
               (apply min-key (fn [city]
                                (-> city
                                    :latlng
                                    (haversine-distance selected-location)))
                      cities))

(add-component! [:div
                 [:h3
                  "Closest city: "
                  [:string {:data-path [:closest-city :name]}]]])

;; Keep clicking the map, and see that the output makes sense.


