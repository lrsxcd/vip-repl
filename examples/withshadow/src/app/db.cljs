(ns app.db
  (:require [re-frame.core :as rf]
            [cljs.pprint :refer [pprint]]
            [clojure.string :as str]))
(defn initial-app-db [] {:data {}
                         :filters {:simple 1}
                         :errors {} :test 1
                         })

;update to 
(defn filter-change->websocket
  "Update filter change to server over websocket"
  [filter-id new-value]
  ;TODO: send a message with the new value
  (pprint {filter-id new-value}))

(def ->web-socket (rf/after filter-change->websocket))

(rf/reg-event-db             ;; sets up initial application state
 :initialize                 ;; usage:  (dispatch [:initialize])
 (fn [_ _]                   ;; the two parameters are not important here, so use _
   {:data {:id 1}
    :filters {}}
    :errors {}))    ;; 


; (rf/reg-event-db                ;; usage:  (dispatch [:time-color-change 34562])
;  :filter-change            ;; dispatched when the user enters a new colour into the UI text field
;  (fn-traced [_ filter-id new-value]  ;; -db event handlers given 2 parameters:  current application state and event (a vector)
;    (assoc-in [:filters filter-id]  new-value)))   ;; compute and return the new application state




(defn h
  [{:keys [db]} [_ filter-id val]]    ;; <--- new: obtain db and id directly
  {:db  (assoc-in db [:filters filter-id] val)}) ;; same as before

(rf/reg-event-fx   ;; a part of the re-frame API
 :update-filter               ;; the kind of event
 ->web-socket
 h)                          ;; the handler function for this kind of event


(defn query-fn
  [db v]         ;; db is current app state, v the query vector
  (:simple (:filters  db)))

(rf/reg-sub  ;; part of the re-frame API
 :filters         ;; query id  
 query-fn)            ;; query fn



; (rf/reg-sub
;  :filter-val
;  (fn [{:keys [db]} [_ filter-id]]
;    (filter-id (:filters db))))
; (rf/reg-sub ;Should return the value of a filter with id=filter-id
;  :filter-val 
;  (fn [db _]
;    (:numeric (:filters  db))))

(rf/reg-sub
 :filter-val
 (fn [db [_ filter-id]]
   (filter-id (:filters  db))))

(rf/reg-sub
 :test-reg
 (fn [db [_ color]]
   (println (pr-str color))
   color))

(rf/reg-sub
 :get-numeric-value
 (fn [db _]
   (:numeric (:filters  db))))