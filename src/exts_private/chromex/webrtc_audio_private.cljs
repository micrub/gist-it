(ns chromex.webrtc-audio-private (:require-macros [chromex.webrtc-audio-private :refer [gen-wrap]])
    (:require [chromex-lib.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn get-sinks* [config]
  (gen-wrap :function ::get-sinks config))

(defn get-active-sink* [config request]
  (gen-wrap :function ::get-active-sink config request))

(defn set-active-sink* [config request sink-id]
  (gen-wrap :function ::set-active-sink config request sink-id))

(defn get-associated-sink* [config security-origin source-id-in-origin]
  (gen-wrap :function ::get-associated-sink config security-origin source-id-in-origin))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-sinks-changed* [config channel]
  (gen-wrap :event ::on-sinks-changed config channel))

