(ns chromex.app.input-method-private (:require-macros [chromex.app.input-method-private :refer [gen-wrap]])
    (:require [chromex.core]))

; -- functions --------------------------------------------------------------------------------------------------------------

(defn get-input-method-config* [config]
  (gen-wrap :function ::get-input-method-config config))

(defn get-input-methods* [config]
  (gen-wrap :function ::get-input-methods config))

(defn get-current-input-method* [config]
  (gen-wrap :function ::get-current-input-method config))

(defn set-current-input-method* [config input-method-id]
  (gen-wrap :function ::set-current-input-method config input-method-id))

(defn fetch-all-dictionary-words* [config]
  (gen-wrap :function ::fetch-all-dictionary-words config))

(defn add-word-to-dictionary* [config word]
  (gen-wrap :function ::add-word-to-dictionary config word))

(defn get-encrypt-sync-enabled* [config]
  (gen-wrap :function ::get-encrypt-sync-enabled config))

(defn set-xkb-layout* [config xkb-name]
  (gen-wrap :function ::set-xkb-layout config xkb-name))

(defn finish-composing-text* [config parameters]
  (gen-wrap :function ::finish-composing-text config parameters))

(defn set-selection-range* [config parameters]
  (gen-wrap :function ::set-selection-range config parameters))

(defn notify-ime-menu-item-activated* [config engine-id name]
  (gen-wrap :function ::notify-ime-menu-item-activated config engine-id name))

(defn show-input-view* [config]
  (gen-wrap :function ::show-input-view config))

(defn open-options-page* [config input-method-id]
  (gen-wrap :function ::open-options-page config input-method-id))

(defn get-composition-bounds* [config]
  (gen-wrap :function ::get-composition-bounds config))

(defn get-surrounding-text* [config before-length after-length]
  (gen-wrap :function ::get-surrounding-text config before-length after-length))

(defn get-setting* [config engine-id key]
  (gen-wrap :function ::get-setting config engine-id key))

(defn set-setting* [config engine-id key value]
  (gen-wrap :function ::set-setting config engine-id key value))

(defn set-composition-range* [config parameters]
  (gen-wrap :function ::set-composition-range config parameters))

; -- events -----------------------------------------------------------------------------------------------------------------

(defn on-changed* [config channel & args]
  (gen-wrap :event ::on-changed config channel args))

(defn on-composition-bounds-changed* [config channel & args]
  (gen-wrap :event ::on-composition-bounds-changed config channel args))

(defn on-dictionary-loaded* [config channel & args]
  (gen-wrap :event ::on-dictionary-loaded config channel args))

(defn on-dictionary-changed* [config channel & args]
  (gen-wrap :event ::on-dictionary-changed config channel args))

(defn on-ime-menu-activation-changed* [config channel & args]
  (gen-wrap :event ::on-ime-menu-activation-changed config channel args))

(defn on-ime-menu-list-changed* [config channel & args]
  (gen-wrap :event ::on-ime-menu-list-changed config channel args))

(defn on-ime-menu-items-changed* [config channel & args]
  (gen-wrap :event ::on-ime-menu-items-changed config channel args))

(defn on-focus* [config channel & args]
  (gen-wrap :event ::on-focus config channel args))

(defn on-settings-changed* [config channel & args]
  (gen-wrap :event ::on-settings-changed config channel args))

(defn on-screen-projection-changed* [config channel & args]
  (gen-wrap :event ::on-screen-projection-changed config channel args))

