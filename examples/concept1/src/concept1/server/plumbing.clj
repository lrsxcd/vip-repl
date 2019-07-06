(ns concept1.server.plumbing
  (:require [plumbing.fnk.pfnk :as pfnk]
            [plumbing.core :refer [fnk]]
            [plumbing.graph :as graph]
            [com.rpl.specter :as specter
             :refer [transform ALL MAP-KEYS]]))

(defn memoize-fnk [f]
  (pfnk/fn->fnk (memoize f) (pfnk/io-schemata f)))

(def reactions (atom {}))

(defn ->flow []
  (if (empty? @reactions)
    identity
    (graph/compile @reactions)))

(def current-flow (atom (->flow)))

(defn flow [data]
  (@current-flow data))

(defn update-flow! []
  (reset! current-flow (->flow)))

(defn clear! []
  (reset! reactions {})
  (update-flow!))

(defmacro safe-fnk [argnames & body]
  (concat [`fnk]
          [(mapv (fn [k] {k nil}) argnames)]
          `((when (every? some? ~(mapv symbol argnames))
              ~@body))))

(comment
  ((safe-fnk [x] (inc x)) {:x 1})
  ;;=> 2
  ((safe-fnk [x] (inc x)) {})
  ;;=> nil
  )

(defmacro add-reaction! [rname argnames & body]
  `(do (swap! reactions assoc
              ~(keyword rname) (memoize-fnk
                                (safe-fnk ~argnames ~@body)))
       (update-flow!)))

(defn reaction->keys [reaction]
  (->> reaction
       meta
       :schema
       :input-schemas
       first
       first
       :schema
       keys))


(comment

  (macroexpand-1 '(add-reaction! y [x] (inc x))) 

  (add-reaction! y [x] (inc x))

  (flow {:x 1})

  (flow {})

  (clear!)

  )

