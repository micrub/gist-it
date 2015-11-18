(ns chromex.devtools.panels (:require-macros [chromex.devtools.panels :refer [gen-wrap]])
    (:require [chromex-lib.core]))

; -- properties -----------------------------------------------------------------------------------------------------

(defn elements* [config]
  (gen-wrap :property ::elements config))

(defn sources* [config]
  (gen-wrap :property ::sources config))

; -- functions ------------------------------------------------------------------------------------------------------

(defn create* [config title icon-path page-path]
  (gen-wrap :function ::create config title icon-path page-path))

(defn set-open-resource-handler* [config]
  (gen-wrap :function ::set-open-resource-handler config))

(defn open-resource* [config url line-number]
  (gen-wrap :function ::open-resource config url line-number))

