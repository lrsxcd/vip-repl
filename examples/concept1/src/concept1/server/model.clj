(ns concept1.server.model
  (:require
   [clojure.core.memoize :as memo]
   [clojure.set :as set]
   [concept1.server.plumbing :as plumbing]))

(def state
  (atom {}))

(defn compute-reactions! []
  (swap! state
         update :data
         (fn [pre-reactions-data]
           (merge pre-reactions-data
                  (plumbing/flow pre-reactions-data)))))

(defn assoc-in! [path new-val]
  (swap! state assoc-in (cons :data path) new-val))

(defn next-uid [{:keys [params]}]
  (java.util.UUID/randomUUID))

(defn clear-datasets! []
  (swap! state
         assoc
         :data {}))

(defn clear-components! []
  (swap! state
         assoc
         :components []))

(defn add-dataset! [dataset-name dataset]
  (swap! state
         update
         :data #(assoc % dataset-name dataset)))

(defn add-component! [component-form]
  (swap! state
         update
         :components #(conj % component-form)))

(defn clear! []
  (plumbing/clear!)
  (clear-components!)
  (clear-datasets!))

