(ns ^:figwheel-always concept1.client.main
  (:require
   [concept1.client.ainit]
   [concept1.client.view :as view]
   [reagent.core :as reagent :refer [atom]]))

(defn start []
  (reagent/render-component [view/main]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported ff
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
