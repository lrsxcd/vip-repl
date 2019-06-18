(ns vip-repl.utils.filter
  (:require [reagent.core :as reagent]
            [re-frame.core :as rf]
            [clojure.string :as str]))


(defn dispatch-filter-change
  [filter-id value
   (rf/dispatch [:chage-filter value])])  ;; <-- dispatch used

;this method is not filter related
(rf/reg-event-db             
 :initialize                 
 (fn [_ _]                   
   {:data data :filters {} :client-state {}}))


(rf/reg-event-db               
 :filter-id-change            
 (fn [db [_ new-value]]  
   (assoc db 'filter-id  new-value)))   


(defn create-event-dispacher "" [:filter-id])



(defn create-filter "Create a filter with event dispacher event handler and co-efect handler" [{keys [id type]}])

         


