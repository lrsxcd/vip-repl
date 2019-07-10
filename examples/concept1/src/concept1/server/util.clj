(ns concept1.server.util
  (:require [fastmath.core :refer [cos asin sqrt]]))

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


