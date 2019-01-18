(ns chromex.app.usb
  "Use the chrome.usb API to interact with connected USB
   devices. This API provides access to USB operations from within the context
   of an app. Using this API, apps can function as drivers for hardware devices.

   Errors generated by this API are reported by setting
   'runtime.lastError' and executing the function's regular callback. The
   callback's regular parameters will be undefined in this case.

     * available since Chrome 28
     * https://developer.chrome.com/apps/usb"

  (:refer-clojure :only [defmacro defn apply declare meta let partial])
  (:require [chromex.wrapgen :refer [gen-wrap-helper]]
            [chromex.callgen :refer [gen-call-helper gen-tap-all-events-call]]))

(declare api-table)
(declare gen-call)

; -- functions --------------------------------------------------------------------------------------------------------------

(defmacro get-devices
  "Enumerates connected USB devices.

     |options| - The properties to search for on target devices.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [devices] where:

     |devices| - https://developer.chrome.com/apps/usb#property-callback-devices.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-getDevices."
  ([options] (gen-call :function ::get-devices &form options)))

(defmacro get-user-selected-devices
  "Presents a device picker to the user and returns the 'Device's selected. If the user cancels the picker devices will be
   empty. A user gesture is required for the dialog to display. Without a user gesture, the callback will run as though the
   user cancelled.

     |options| - Configuration of the device picker dialog box.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [devices] where:

     |devices| - https://developer.chrome.com/apps/usb#property-callback-devices.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-getUserSelectedDevices."
  ([options] (gen-call :function ::get-user-selected-devices &form options)))

(defmacro get-configurations
  "Returns the full set of device configuration descriptors.

     |device| - The 'Device' to fetch descriptors from.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [configs] where:

     |configs| - https://developer.chrome.com/apps/usb#property-callback-configs.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-getConfigurations."
  ([device] (gen-call :function ::get-configurations &form device)))

(defmacro request-access
  "Requests access from the permission broker to a device claimed by Chrome OS if the given interface on the device is not
   claimed.

     |device| - The 'Device' to request access to.
     |interface-id| - The particular interface requested.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [success] where:

     |success| - https://developer.chrome.com/apps/usb#property-callback-success.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-requestAccess."
  ([device interface-id] (gen-call :function ::request-access &form device interface-id)))

(defmacro open-device
  "Opens a USB device returned by 'getDevices'.

     |device| - The 'Device' to open.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [handle] where:

     |handle| - https://developer.chrome.com/apps/usb#property-callback-handle.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-openDevice."
  ([device] (gen-call :function ::open-device &form device)))

(defmacro find-devices
  "Finds USB devices specified by the vendor, product and (optionally) interface IDs and if permissions allow opens them for
   use.If the access request is rejected or the device fails to be opened a connection handle will not be created or
   returned.Calling this method is equivalent to calling 'getDevices' followed by 'openDevice' for each device.

     |options| - The properties to search for on target devices.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [handles] where:

     |handles| - https://developer.chrome.com/apps/usb#property-callback-handles.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-findDevices."
  ([options] (gen-call :function ::find-devices &form options)))

(defmacro close-device
  "Closes a connection handle. Invoking operations on a handle after it has been closed is a safe operation but causes no
   action to be taken.

     |handle| - The 'ConnectionHandle' to close.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-closeDevice."
  ([handle] (gen-call :function ::close-device &form handle)))

(defmacro set-configuration
  "Select a device configuration.This function effectively resets the device by selecting one of the device's available
   configurations. Only configuration values greater than 0 are valid however some buggy devices have a working configuration
   0 and so this value is allowed.

     |handle| - An open connection to the device.
     |configuration-value| - https://developer.chrome.com/apps/usb#property-setConfiguration-configurationValue.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-setConfiguration."
  ([handle configuration-value] (gen-call :function ::set-configuration &form handle configuration-value)))

(defmacro get-configuration
  "Gets the configuration descriptor for the currently selected configuration.

     |handle| - An open connection to the device.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [config] where:

     |config| - https://developer.chrome.com/apps/usb#property-callback-config.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-getConfiguration."
  ([handle] (gen-call :function ::get-configuration &form handle)))

(defmacro list-interfaces
  "Lists all interfaces on a USB device.

     |handle| - An open connection to the device.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [descriptors] where:

     |descriptors| - https://developer.chrome.com/apps/usb#property-callback-descriptors.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-listInterfaces."
  ([handle] (gen-call :function ::list-interfaces &form handle)))

(defmacro claim-interface
  "Claims an interface on a USB device. Before data can be transfered to an interface or associated endpoints the interface
   must be claimed. Only one connection handle can claim an interface at any given time. If the interface is already claimed,
   this call will fail.'releaseInterface' should be called when the interface is no longer needed.

     |handle| - An open connection to the device.
     |interface-number| - The interface to be claimed.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-claimInterface."
  ([handle interface-number] (gen-call :function ::claim-interface &form handle interface-number)))

(defmacro release-interface
  "Releases a claimed interface.

     |handle| - An open connection to the device.
     |interface-number| - The interface to be released.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-releaseInterface."
  ([handle interface-number] (gen-call :function ::release-interface &form handle interface-number)))

(defmacro set-interface-alternate-setting
  "Selects an alternate setting on a previously claimed interface.

     |handle| - An open connection to the device where this interface has been     claimed.
     |interface-number| - The interface to configure.
     |alternate-setting| - The alternate setting to configure.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [].

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-setInterfaceAlternateSetting."
  ([handle interface-number alternate-setting] (gen-call :function ::set-interface-alternate-setting &form handle interface-number alternate-setting)))

(defmacro control-transfer
  "Performs a control transfer on the specified device.Control transfers refer to either the device, an interface or an
   endpoint. Transfers to an interface or endpoint require the interface to be claimed.

     |handle| - An open connection to the device.
     |transfer-info| - https://developer.chrome.com/apps/usb#property-controlTransfer-transferInfo.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [info] where:

     |info| - https://developer.chrome.com/apps/usb#property-callback-info.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-controlTransfer."
  ([handle transfer-info] (gen-call :function ::control-transfer &form handle transfer-info)))

(defmacro bulk-transfer
  "Performs a bulk transfer on the specified device.

     |handle| - An open connection to the device.
     |transfer-info| - The transfer parameters.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [info] where:

     |info| - https://developer.chrome.com/apps/usb#property-callback-info.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-bulkTransfer."
  ([handle transfer-info] (gen-call :function ::bulk-transfer &form handle transfer-info)))

(defmacro interrupt-transfer
  "Performs an interrupt transfer on the specified device.

     |handle| - An open connection to the device.
     |transfer-info| - The transfer parameters.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [info] where:

     |info| - https://developer.chrome.com/apps/usb#property-callback-info.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-interruptTransfer."
  ([handle transfer-info] (gen-call :function ::interrupt-transfer &form handle transfer-info)))

(defmacro isochronous-transfer
  "Performs an isochronous transfer on the specific device.

     |handle| - An open connection to the device.
     |transfer-info| - https://developer.chrome.com/apps/usb#property-isochronousTransfer-transferInfo.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [info] where:

     |info| - https://developer.chrome.com/apps/usb#property-callback-info.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-isochronousTransfer."
  ([handle transfer-info] (gen-call :function ::isochronous-transfer &form handle transfer-info)))

(defmacro reset-device
  "Tries to reset the USB device. If the reset fails, the given connection handle will be closed and the  USB device will
   appear to be disconnected then reconnected.  In this case 'getDevices' or 'findDevices' must be called again to acquire the
   device.

     |handle| - A connection handle to reset.

   This function returns a core.async channel of type `promise-chan` which eventually receives a result value.
   Signature of the result value put on the channel is [success] where:

     |success| - https://developer.chrome.com/apps/usb#property-callback-success.

   In case of an error the channel closes without receiving any value and relevant error object can be obtained via
   chromex.error/get-last-error.

   https://developer.chrome.com/apps/usb#method-resetDevice."
  ([handle] (gen-call :function ::reset-device &form handle)))

; -- events -----------------------------------------------------------------------------------------------------------------
;
; docs: https://github.com/binaryage/chromex/#tapping-events

(defmacro tap-on-device-added-events
  "Event generated when a device is added to the system. Events are only broadcast to apps and extensions that have permission
   to access the device. Permission may have been granted at install time, when the user accepted an optional permission (see
   'permissions.request'), or through 'getUserSelectedDevices'.

   Events will be put on the |channel| with signature [::on-device-added [device]] where:

     |device| - https://developer.chrome.com/apps/usb#property-onDeviceAdded-device.

   Note: |args| will be passed as additional parameters into Chrome event's .addListener call.

   https://developer.chrome.com/apps/usb#event-onDeviceAdded."
  ([channel & args] (apply gen-call :event ::on-device-added &form channel args)))

(defmacro tap-on-device-removed-events
  "Event generated when a device is removed from the system. See 'onDeviceAdded' for which events are delivered.

   Events will be put on the |channel| with signature [::on-device-removed [device]] where:

     |device| - https://developer.chrome.com/apps/usb#property-onDeviceRemoved-device.

   Note: |args| will be passed as additional parameters into Chrome event's .addListener call.

   https://developer.chrome.com/apps/usb#event-onDeviceRemoved."
  ([channel & args] (apply gen-call :event ::on-device-removed &form channel args)))

; -- convenience ------------------------------------------------------------------------------------------------------------

(defmacro tap-all-events
  "Taps all valid non-deprecated events in chromex.app.usb namespace."
  [chan]
  (gen-tap-all-events-call api-table (meta &form) chan))

; ---------------------------------------------------------------------------------------------------------------------------
; -- API TABLE --------------------------------------------------------------------------------------------------------------
; ---------------------------------------------------------------------------------------------------------------------------

(def api-table
  {:namespace "chrome.usb",
   :since "28",
   :functions
   [{:id ::get-devices,
     :name "getDevices",
     :since "31",
     :callback? true,
     :params
     [{:name "options", :type "object"}
      {:name "callback", :type :callback, :callback {:params [{:name "devices", :type "[array-of-usb.Devices]"}]}}]}
    {:id ::get-user-selected-devices,
     :name "getUserSelectedDevices",
     :since "40",
     :callback? true,
     :params
     [{:name "options", :type "object"}
      {:name "callback", :type :callback, :callback {:params [{:name "devices", :type "[array-of-usb.Devices]"}]}}]}
    {:id ::get-configurations,
     :name "getConfigurations",
     :since "47",
     :callback? true,
     :params
     [{:name "device", :type "usb.Device"}
      {:name "callback",
       :type :callback,
       :callback {:params [{:name "configs", :type "[array-of-usb.ConfigDescriptors]"}]}}]}
    {:id ::request-access,
     :name "requestAccess",
     :since "40",
     :deprecated
     "This function was Chrome OS specific and calling it on other\\n    platforms would fail. This operation is now implicitly performed as part of\\n    'openDevice' and this function will return true on all\\n    platforms.",
     :callback? true,
     :params
     [{:name "device", :type "usb.Device"}
      {:name "interface-id", :type "integer"}
      {:name "callback", :type :callback, :callback {:params [{:name "success", :type "boolean"}]}}]}
    {:id ::open-device,
     :name "openDevice",
     :since "31",
     :callback? true,
     :params
     [{:name "device", :type "usb.Device"}
      {:name "callback", :type :callback, :callback {:params [{:name "handle", :type "usb.ConnectionHandle"}]}}]}
    {:id ::find-devices,
     :name "findDevices",
     :callback? true,
     :params
     [{:name "options", :type "object"}
      {:name "callback",
       :type :callback,
       :callback {:params [{:name "handles", :type "[array-of-usb.ConnectionHandles]"}]}}]}
    {:id ::close-device,
     :name "closeDevice",
     :callback? true,
     :params [{:name "handle", :type "usb.ConnectionHandle"} {:name "callback", :optional? true, :type :callback}]}
    {:id ::set-configuration,
     :name "setConfiguration",
     :since "42",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "configuration-value", :type "integer"}
      {:name "callback", :type :callback}]}
    {:id ::get-configuration,
     :name "getConfiguration",
     :since "39",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "callback", :type :callback, :callback {:params [{:name "config", :type "usb.ConfigDescriptor"}]}}]}
    {:id ::list-interfaces,
     :name "listInterfaces",
     :since "29",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "callback",
       :type :callback,
       :callback {:params [{:name "descriptors", :type "[array-of-usb.InterfaceDescriptors]"}]}}]}
    {:id ::claim-interface,
     :name "claimInterface",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "interface-number", :type "integer"}
      {:name "callback", :type :callback}]}
    {:id ::release-interface,
     :name "releaseInterface",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "interface-number", :type "integer"}
      {:name "callback", :type :callback}]}
    {:id ::set-interface-alternate-setting,
     :name "setInterfaceAlternateSetting",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "interface-number", :type "integer"}
      {:name "alternate-setting", :type "integer"}
      {:name "callback", :type :callback}]}
    {:id ::control-transfer,
     :name "controlTransfer",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "transfer-info", :type "object"}
      {:name "callback", :type :callback, :callback {:params [{:name "info", :type "usb.TransferResultInfo"}]}}]}
    {:id ::bulk-transfer,
     :name "bulkTransfer",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "transfer-info", :type "usb.GenericTransferInfo"}
      {:name "callback", :type :callback, :callback {:params [{:name "info", :type "usb.TransferResultInfo"}]}}]}
    {:id ::interrupt-transfer,
     :name "interruptTransfer",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "transfer-info", :type "usb.GenericTransferInfo"}
      {:name "callback", :type :callback, :callback {:params [{:name "info", :type "usb.TransferResultInfo"}]}}]}
    {:id ::isochronous-transfer,
     :name "isochronousTransfer",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "transfer-info", :type "object"}
      {:name "callback", :type :callback, :callback {:params [{:name "info", :type "usb.TransferResultInfo"}]}}]}
    {:id ::reset-device,
     :name "resetDevice",
     :callback? true,
     :params
     [{:name "handle", :type "usb.ConnectionHandle"}
      {:name "callback", :type :callback, :callback {:params [{:name "success", :type "boolean"}]}}]}],
   :events
   [{:id ::on-device-added, :name "onDeviceAdded", :since "42", :params [{:name "device", :type "usb.Device"}]}
    {:id ::on-device-removed, :name "onDeviceRemoved", :since "42", :params [{:name "device", :type "usb.Device"}]}]})

; -- helpers ----------------------------------------------------------------------------------------------------------------

; code generation for native API wrapper
(defmacro gen-wrap [kind item-id config & args]
  (apply gen-wrap-helper api-table kind item-id config args))

; code generation for API call-site
(def gen-call (partial gen-call-helper api-table))