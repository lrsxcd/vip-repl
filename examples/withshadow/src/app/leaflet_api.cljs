(ns app.leaflet-api)

(defn create-map
  "Create HTML snippet for react-leaflet map

  Arguments:
      center_lat {float} -- map center latitude
      center_lng {float} -- map center logntitude
      zoom {int} -- zoom in number between 1 and 30
      markers {string} -- markers that are created with create_markers
      filters {string} -- filters that are created with create_filters 
  
  Returns:
      string  -- html snippet of a react-leaflet string
  "
  [center_lat center_lng zoom markers filters]
  (format "<map center=[%.8f, %.8f zoom=%d]>%s</map>%s"
          center_lat center_lng zoom markers filters))


(defn create-markers
  "Create markers HTML snippet

  Arguments:
      data {list of maps} -- list of marker
      lat_key {str} -- key for latitude
      lng_key {str} -- key for longitude
  "
  ([data] (create-markers data :lat :lng))
  ([data lat_key lng_key]
   (transduce (comp
               (filter #(and (% lat_key) (% lng_key)))
               (map #(format "<marker center=[%.8f, %.8f]></marker>"
                             (% lat_key) (% lng_key))))
              str data)))

(defn create-filter
  "Create a filter and functionality

  Arguments:
    ftype {str} -- options are  

  Keyword Arguments:
      on_change {[type]} -- [description] (default: {None})
  "
  ([ftype] (create-filter ftype nil))
  ([ftype on-change]
   (if (= ftype "input")
     "<input />"
     "<input />")))


;;; below are testing data and functions


(def ll-data
  [{:lat 32.314159265427 :lng 35.5}
   {:lat 17.123 :lng 67.7}
   {:alat 42.13 :lnga 5.5}])

(def ll-data-2
  [{"latitude" 32.3 "longitude" 35.5}
   {"latitude-x" 17.123 "longitude" 67.7}
   {:lat 42.13 :lng 5.5}])


(defn test-all
  "Testing Leaflet API"
  [& args]
  (println "Testing Leaflet API")
  (println "Testing (create-map)...")
  (println (create-map 32.314159265427 34.7 42 "markers" "filters"))
  (println)
  (println "Testing (create-markers)...")
  (println (create-markers ll-data))
  (println)
  (println (create-markers ll-data :lat :lng))
  (println)
  (println (create-markers ll-data-2 "latitude" "longitude"))
  (println)
  (println "Testing create-filter")
  (println (create-filter "input"))
  (println (create-filter "input" "hello"))
  (println (create-filter "input" nil))
  (println (create-filter "bad input"))
  )



