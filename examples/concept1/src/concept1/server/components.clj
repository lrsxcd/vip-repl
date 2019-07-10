(ns concept1.server.components
  (:require [concept1.server.model :refer [add-component!]]))

;;; amit - proposal for higher-level display functions

(defn make-text-comp
  "Create a text component"
  [text data-path]
  [:div.comp.text
   [:string {:data-path data-path
             :fmt-txt text}]])

(defn display-text
  "Display a text component"
  [text data-path]
  (add-component! (make-text-comp text data-path)))


(defn make-map-comp
  "Create a map component with given attributes"
  [& {:keys [center zoom circle-markers selection-path]
      :or {center [51.48 0] zoom 3}}]
  [:div.comp.map
   [:leaflet {:center center
              :zoom zoom
              :circle-markers circle-markers
              :selection-path selection-path}]])

(defn append-comp-container
  "Append components to an existing components container"
  [container & components]
  (into container components))

(defn make-comp-container
  "Create a container component"
  [& components]
  (into [:div.comp-container] components))

(defn display-map
  "Display a map with given attributes"
  [& {:keys [center zoom circle-markers selection-path]
      :or {center [51.48 0] zoom 3}}]
  (add-component! (make-map-comp center zoom circle-markers selection-path)))




