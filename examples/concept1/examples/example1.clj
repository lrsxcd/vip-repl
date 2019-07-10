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

;;; Notes

;; You need to `npm install vega vega-lite vega-embed vega-tooltip`

;; Run the code in this file selectievly, follow the scenario (steps
;; are numbered) and see what happens.

;;; Scenario

;;; 1. call once to start server
(start-server!)

;; Now, you can start a client with **npm run watch** (amit: I don't
;; think one needs yarn: `npm yarn watch`; we should either use npm or
;; yarn), and open `localhost:8700` in the browser.

;; At any moment, you can clear all data, reactions and components
;; with this command:
(comment
  (clear!))

;;; or clear only the components with
(comment
  (clear-components!))

;; 2. Add a dataset
(add-dataset! :cities [{:name       "Milan"
                        :population 1156903
                        :latlng     [45.466667 9.2]}
                       {:name       "Warsaw"
                        :population 165167
                        :latlng     [52.25 21]}
                       {:name "Oslo"
                        :population 673000
                        :latlng [59.92 10.73]}])

;;; 3. Add another dataset
(add-dataset! :cars [{:make "Suzuki" :model "Jimny" :hp 105 :year 2018}
                     {:make "Citroën" :model "2CV" :hp 10 :year 1965}
                     {:make "Fiat" :model "Panda" :hp 35 :year 1974}])


;; 4. Add a reaction that count # of cities
(add-reaction! n-cities [cities]
               (count cities))

;;; 5. Add a reaction that finds the city which is the closest to the
;;; selected location (defined by clicking on a map)
(add-reaction! closest-city [cities selected-location]
               (apply min-key (fn [city]
                                (-> city
                                    :latlng
                                    (haversine-distance selected-location)))
                      cities))

;; At any stage, you can check the state of the system (currently
;; contains datasets and component definitions:
@state

;; Now add some visual components

;;; 6. Define a map component with city markers
(def map1 (make-map-comp :center [60 11] :zoom 3
                         :circle-markers [{:data-path [:cities]
                                           :LABEL :name}]
                         :selection-path [:selected-location]))

;;; 7. Define text (with values computed by the backend)
(def map1-txt1 (make-text-comp "Location selected: %s"
                               [:selected-location]))

;;; 8. Create a component container containing the map and text
;;; components above
(def cont1 (make-comp-container map1 map1-txt1))

;;; 9. Now add the component to the server's components structure;
;;; this will trigger the display of the componet in the browser
(add-component! cont1)

;;; 10. Create another text component
(def map1-txt2 (make-text-comp "Closest city: %s"
                               [:closest-city :name]))

;;; 11. Add the new text component to the container created
;;; previously, then add the container to the server's list (so it
;;; would be displayed in the browser)
(add-component! (append-comp-container cont1 map1-txt2))

;;; clear all components (leaving the data intact)
;; (clear-components!)

;;; clear everything (components & data)
;; (clear!)

;;; vega test - NEW

;;; 12. create example data
(defn play-data [& names]
  (for [n names i (range 20)]
    {:time i
     :item n
     :quantity (+ (Math/pow (* i (count n)) 0.8) (rand-int (count n)))}))

;;; 13. use the data for creating a vega-lite plot definition
(def line-plot
  {:data {:values (play-data "Monkey" "Cat" "Parrot")}
   :encoding {:x {:field "time"}
              :y {:field "quantity"}
              :color {:field "item" :type "nominal"}}
   :mark "line"})

;;; 14. create a component for the vega-line plot
(def vega-comp [:vega-lite line-plot {:width 450}])

;;; 15. add the vega component, thus causing the frontend to display
;;; it
(add-component! (make-comp-container vega-comp))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; 
;;; amit's scenario end
;;; 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;; amit: not sure about the usefulness of these

;; (display-text "Location selected: %s" [:selected-location])

;; (display-map :center [60 11] :zoom 3
;;              :circle-markers [{:data-path [:cities]
;;                                :LABEL :name}]
;;              :selection-path [:selected-location])

;; (display-map :center [32.8 34.98] :zoom 12)
;; (display-map)


;; (add-component! [:h3 [:string {:data-path [:n-cities]}]
;;                  " cities"])

;;; amit end

(add-component! [:h3 [:string {:data-path [:n-cities]
                               :fmt-txt "found %s cities"}]])

(add-component! [:div
                 [:leaflet {:center         [45 10]
                            :zoom           3
                            :circle-markers
                            [{:data-path [:cities]
                              :LABEL     :name}]
                            :selection-path [:selected-location]}]])

(add-component! [:div
                 [:h3
                  [:string {:data-path [:selected-location]
                            :fmt-txt "Location selected: %s"}]]])

;;; or

(display-text "Location selected: %s" [:selected-location])

;; Click the map in various places, and see the components respond.

;; Let us show the closest city to the selected location:


(add-component! [:div
                 [:h3
                  "Closest city: "
                  [:string {:data-path [:closest-city :name]}]]])

;; Keep clicking the map, and see that the output makes sense.


