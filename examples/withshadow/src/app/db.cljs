(ns app.db
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))
(def initial-app-db [] {:data {}
                     :filters {}}
                     :errors {})


(rf/reg-event-db             ;; sets up initial application state
 :initialize                 ;; usage:  (dispatch [:initialize])
 (fn [_ _]                   ;; the two parameters are not important here, so use _
   {:data {:id 1}
    :filters {}}
    :errors {})    ;; 


(rf/reg-event-db                ;; usage:  (dispatch [:time-color-change 34562])
 :filter-change            ;; dispatched when the user enters a new colour into the UI text field
 (fn [db [fiter-id new-value]]  ;; -db event handlers given 2 parameters:  current application state and event (a vector)
   (assoc db [:filters filter-id]  new-value)))   ;; compute and return the new application state