(ns concept1.server.data
  (:require [cheshire.core :as cheshire]))


(def cities
  (-> "resources/data/cities.geojson"
      slurp
      (cheshire/parse-string keyword)
      :features
      (->> (map (fn [{:keys [geometry id]}]
                  (let [[lat lng] (:coordinates geometry)]
                    {:city id
                     :lat  lat
                     :lng  lng}))))
      delay))

