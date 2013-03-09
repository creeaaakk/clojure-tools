(ns com.creeaaakk.tools.name
  (:require [clojure.string :as s]))

(defn- interposed-name
  [interposer name]
  (println 1)
  (->> name 
       (interpose interposer)
       (apply str)))

(def ^:private lower-interposed-name (comp s/lower-case interposed-name))
(def ^:private upper-interposed-name (comp s/upper-case interposed-name))

(def lower-dash (partial lower-interposed-name "-"))
(def lower-underscore (partial lower-interposed-name "_"))
(def upper-underscore (partial upper-interposed-name "_"))

(defn init-caps
  [strs]
  (println 2)
  (->> strs
       (map s/capitalize)
       s/join))

(defn camel-case
  [[first & rest]]
  (str (s/lower-case first) (init-caps rest)))
