(ns firebase.core
  (:refer-clojure :exclude [ref set update key val])
  (:require [cljsjs.firebase]))

;; ----------------------------------------------------------------------------
;; app

(defn app
  ([cfg] (app cfg "[DEFAULT]"))
  ([cfg name]
   (try
     (.initializeApp js/firebase (clj->js cfg) name)
     (catch js/Error _ (js/firebase.app name)))))



;; LEGACY
;;; ----------------------------------------------------------------------------
;;; ref creation/navigation
;
;(defn ref [s]
;  (js/Firebase. s))
;
;(defn key [r]
;  (.key r))
;
;(defn push [r]
;  (.push r))
;
;(defn child [r & args]
;  (reduce (fn [acc c] (.child acc (if (keyword? c) (name c) c))) r args))
;
;(defn parent [r]
;  (.parent r))
;
;(defn root [r]
;  (.root r))
;
;
;;; ----------------------------------------------------------------------------
;;; mutations
;
;(defn set
;  ([r v] (.set r (clj->js v)))
;  ([r v cb] (.set r (clj->js v) cb)))
;
;(defn update
;  ([r v] (.update r (clj->js v)))
;  ([r v cb] (.update r (clj->js v) cb)))
;
;(defn set-on-disconnect
;  ([r v] (.set (.onDisconnect r) v))
;  ([r v cb] (.set (.onDisconnect r) v cb)))
;
;(defn cancel-on-disconnect
;  ([r] (.cancel (.onDisconnect r)))
;  ([r cb] (.cancel (.onDisconnect r) cb)))
;
;;; ----------------------------------------------------------------------------
;;; reads
;
;(defn on-value
;  ([r cb]
;   (.on r "value" cb))
;  ([r cb cb-err]
;   (.on r "value" cb cb-err)))
;
;(defn once-value
;  ([r cb]
;   (.once r "value" cb))
;  ([r cb cb-err]
;   (.once r "value" cb cb-err)))
;
;(defn off-value [r cb]
;  (.off r "value" cb))
;
;(defn on-child-added
;  ([r cb]
;   (.on r "child_added" cb))
;  ([r cb cb-err]
;   (.on r "child_added" cb cb-err)))
;
;(defn off-child-added [r cb]
;  (.off r "child_added" cb))
;
;
;;; ----------------------------------------------------------------------------
;;; snapshots
;
;(defn val [ss]
;  (-> ss .val (js->clj :keywordize-keys true)))
;
;
;;; ----------------------------------------------------------------------------
;;; authentication
;
;(defn unauth [r]
;  (.unauth r))
;
;(defn xform-auth [raw-auth]
;  (when raw-auth
;    (-> raw-auth
;        (js->clj :keywordize-keys true)
;        (core/update :provider keyword))))
;
;(defn- xform-auth-cb [cb]
;  #(cb %1 (xform-auth %2)))
;
;(defn auth
;  ([r creds]
;   (auth r creds (fn [_ _])))
;  ([r {:keys [token anonymous password oauth options]} cb]
;   (let [opts (if options (clj->js options) #js {})]
;     (cond
;       token
;       (if (string? token)
;         (.authWithCustomToken r token (xform-auth-cb cb) opts)
;         (.error js/console "map form for generating tokens not supported"))
;       password
;       (.authWithPassword r (clj->js password) (xform-auth-cb cb) opts)
;       anonymous
;       (.authAnonymously r (xform-auth-cb cb) opts)
;       oauth
;       (.warn js/console "oauth wrapper not implimented yet")
;       :else
;       (.warn js/console "no auth mechanism provided")))))
;
;(defn get-auth [r]
;  (js->clj (.getAuth r) :keywordize-keys true))
;
;(defn on-auth [r cb]
;  (.onAuth r #(cb (xform-auth %))))
;
;(defn off-auth [r cb]
;  (.offAuth r cb))

