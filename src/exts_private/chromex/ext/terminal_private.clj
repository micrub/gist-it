(ns chromex.ext.terminal-private
  "  * available since Chrome 33"

  (:refer-clojure :only [defmacro defn apply declare meta let partial])
  (:require [chromex.wrapgen :refer [gen-wrap-helper]]
            [chromex.callgen :refer [gen-call-helper gen-tap-all-events-call]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro open-terminal-process
  "Starts new process.

     |process-name| - Name of the process to open. May be 'crosh' or 'vmshell'.
     |args| - Command line arguments to pass to the process.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [id] where:

     |id| - Id of the launched process.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([process-name args] (gen-call :function ::open-terminal-process &form process-name args))
  ([process-name] `(open-terminal-process ~process-name :omit)))

(defmacro close-terminal-process
  "Closes previously opened process.

     |id| - Unique id of the process we want to close.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [success] where:

     |success| - ?

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([id] (gen-call :function ::close-terminal-process &form id)))

(defmacro send-input
  "Sends input that will be routed to stdin of the process with the specified id.

     |id| - The id of the process to which we want to send input.
     |input| - Input we are sending to the process.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [success] where:

     |success| - ?

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([id input] (gen-call :function ::send-input &form id input)))

(defmacro on-terminal-resize
  "Notify the process with the id id that terminal window size has changed.

     |id| - The id of the process.
     |width| - New window width (as column count).
     |height| - New window height (as row count).

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [success] where:

     |success| - ?

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([id width height] (gen-call :function ::on-terminal-resize &form id width height)))

(defmacro ack-output
  "Called from |onProcessOutput| when the event is dispatched to terminal extension. Observing the terminal process output
   will be paused after |onProcessOutput| is dispatched until this method is called.

     |tab-id| - Tab ID from |onProcessOutput| event.
     |id| - The id of the process to which |onProcessOutput| was dispatched."
  ([tab-id id] (gen-call :function ::ack-output &form tab-id id)))

(defmacro get-crosh-settings
  "Returns an object (DictionaryValue) containing UI settings such as font style and color used by the crosh extension.  This
   function is called by the terminal system app the first time it is run to migrate any previous settings.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [settings] where:

     |settings| - ?

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([] (gen-call :function ::get-crosh-settings &form)))

(defmacro get-settings
  "Returns an object (DictionaryValue) containing UI settings such as font style and colors used by terminal and stored as a
   syncable pref.  The UI currently has ~70 properties and we wish to allow flexibility for these to change in the UI without
   updating this API, so we allow any properties.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [settings] where:

     |settings| - Settings from prefs.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([] (gen-call :function ::get-settings &form)))

(defmacro set-settings
  "Sets terminal UI settings which are stored as a syncable pref.

     |settings| - Settings to update into prefs.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error."
  ([settings] (gen-call :function ::set-settings &form settings)))

; -- events -----------------------------------------------------------------------------------------------------------------
;
; docs: https://github.com/binaryage/chromex/#tapping-events

(defmacro tap-on-process-output-events
  "Fired when an opened process writes something to its output. Observing further process output will be blocked until
   |ackOutput| for the terminal is called. Internally, first event argument will be ID of the tab that contains terminal
   instance for which this event is intended. This argument will be stripped before passing the event to the extension.

   Events will be put on the |channel| with signature [::on-process-output [id type text]] where:

     |id| - Id of the process from which the output came.
     |type| - Type of the output stream from which output came. When process exits, output type will be set to exit
     |text| - Text that was written to the output stream.

   Note: |args| will be passed as additional parameters into Chrome event's .addListener call."
  ([channel & args] (apply gen-call :event ::on-process-output &form channel args)))

(defmacro tap-on-settings-changed-events
  "Fired when terminal UI settings change.

   Events will be put on the |channel| with signature [::on-settings-changed [settings]] where:

     |settings| - Terminal UI Settings with updated values.

   Note: |args| will be passed as additional parameters into Chrome event's .addListener call."
  ([channel & args] (apply gen-call :event ::on-settings-changed &form channel args)))

; -- convenience ------------------------------------------------------------------------------------------------------------

(defmacro tap-all-events
  "Taps all valid non-deprecated events in chromex.ext.terminal-private namespace."
  [chan]
  (gen-tap-all-events-call api-table (meta &form) chan))

; ---------------------------------------------------------------------------------------------------------------------------
; -- API TABLE --------------------------------------------------------------------------------------------------------------
; ---------------------------------------------------------------------------------------------------------------------------

(def api-table
  {:namespace "chrome.terminalPrivate",
   :since "33",
   :functions
   [{:id ::open-terminal-process,
     :name "openTerminalProcess",
     :callback? true,
     :params
     [{:name "process-name", :type "string"}
      {:name "args", :optional? true, :since "66", :type "[array-of-strings]"}
      {:name "callback", :type :callback, :callback {:params [{:name "id", :type "string"}]}}]}
    {:id ::close-terminal-process,
     :name "closeTerminalProcess",
     :callback? true,
     :params
     [{:name "id", :since "74", :type "string"}
      {:name "callback", :optional? true, :type :callback, :callback {:params [{:name "success", :type "boolean"}]}}]}
    {:id ::send-input,
     :name "sendInput",
     :callback? true,
     :params
     [{:name "id", :since "74", :type "string"}
      {:name "input", :type "string"}
      {:name "callback", :optional? true, :type :callback, :callback {:params [{:name "success", :type "boolean"}]}}]}
    {:id ::on-terminal-resize,
     :name "onTerminalResize",
     :callback? true,
     :params
     [{:name "id", :since "74", :type "string"}
      {:name "width", :type "integer"}
      {:name "height", :type "integer"}
      {:name "callback", :optional? true, :type :callback, :callback {:params [{:name "success", :type "boolean"}]}}]}
    {:id ::ack-output,
     :name "ackOutput",
     :since "49",
     :params [{:name "tab-id", :type "integer"} {:name "id", :since "74", :type "string"}]}
    {:id ::get-crosh-settings,
     :name "getCroshSettings",
     :since "future",
     :callback? true,
     :params [{:name "callback", :type :callback, :callback {:params [{:name "settings", :type "object"}]}}]}
    {:id ::get-settings,
     :name "getSettings",
     :since "master",
     :callback? true,
     :params [{:name "callback", :type :callback, :callback {:params [{:name "settings", :type "object"}]}}]}
    {:id ::set-settings,
     :name "setSettings",
     :since "master",
     :callback? true,
     :params [{:name "settings", :type "object"} {:name "callback", :type :callback}]}],
   :events
   [{:id ::on-process-output,
     :name "onProcessOutput",
     :params
     [{:name "id", :since "74", :type "string"}
      {:name "type", :type "terminalPrivate.OutputType"}
      {:name "text", :type "string"}]}
    {:id ::on-settings-changed,
     :name "onSettingsChanged",
     :since "master",
     :params [{:name "settings", :type "object"}]}]})

; -- helpers ----------------------------------------------------------------------------------------------------------------

; code generation for native API wrapper
(defmacro gen-wrap [kind item-id config & args]
  (apply gen-wrap-helper api-table kind item-id config args))

; code generation for API call-site
(def gen-call (partial gen-call-helper api-table))