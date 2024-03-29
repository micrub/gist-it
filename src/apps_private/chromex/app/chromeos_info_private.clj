(ns chromex.app.chromeos-info-private
  "  * available since Chrome 33"

  (:refer-clojure :only [defmacro defn apply declare meta let partial])
  (:require [chromex.wrapgen :refer [gen-wrap-helper]]
            [chromex.callgen :refer [gen-call-helper gen-tap-all-events-call]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro get
  "Fetches customization values for the given property names. See property names in the declaration of the returned
   dictionary.

     |property-names| - Chrome OS Property names

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [properties-dictionary] where:

     |properties-dictionary| - Dictionary which contains all requested properties

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([property-names] (gen-call :function ::get &form property-names)))

(defmacro set
  "Sets values for the given system property.

     |property-name| - Chrome OS system property name
     |property-value| - Chrome OS system property value"
  ([property-name property-value] (gen-call :function ::set &form property-name property-value)))

; -- convenience ------------------------------------------------------------------------------------------------------------

(defmacro tap-all-events
  "Taps all valid non-deprecated events in chromex.app.chromeos-info-private namespace."
  [chan]
  (gen-tap-all-events-call api-table (meta &form) chan))

; ---------------------------------------------------------------------------------------------------------------------------
; -- API TABLE --------------------------------------------------------------------------------------------------------------
; ---------------------------------------------------------------------------------------------------------------------------

(def api-table
  {:namespace "chrome.chromeosInfoPrivate",
   :since "33",
   :functions
   [{:id ::get,
     :name "get",
     :callback? true,
     :params
     [{:name "property-names", :type "[array-of-strings]"}
      {:name "callback", :type :callback, :callback {:params [{:name "properties-dictionary", :type "object"}]}}]}
    {:id ::set,
     :name "set",
     :params
     [{:name "property-name", :type "chromeosInfoPrivate.PropertyName"}
      {:name "property-value", :since "34", :type "any"}]}]})

; -- helpers ----------------------------------------------------------------------------------------------------------------

; code generation for native API wrapper
(defmacro gen-wrap [kind item-id config & args]
  (apply gen-wrap-helper api-table kind item-id config args))

; code generation for API call-site
(def gen-call (partial gen-call-helper api-table))