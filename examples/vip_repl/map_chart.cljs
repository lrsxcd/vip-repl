(ns vip-repl.map-chart
  :requires [vip-repl.utils as])
(defn ->context [{:keys [name]}])

(defn ->map-cart [{:keys [context center-lat center-lng zoom]}]
  "creates a a visual map components")
(defn ->map-marker [{:keys [map latitude longtitude icon color text]}]
  "createa single marker on a map")
(defn marker-from-data [{:keys [map data-keys lat-fld lng-fld color-fn  ]}]
  "createa single marker on a map")
