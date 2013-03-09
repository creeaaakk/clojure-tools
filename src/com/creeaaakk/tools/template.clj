(ns com.creeaaakk.tools.template)

(declare gen gen-list)

(defn- seq-vec?
  [sv]
  (or (seq? sv) (vector? sv)))

(defn- is-var?
  [v]
  (and (symbol? v) (= \? (first (name v)))))

(defn gen
  ([data template formats]
   (gen data template formats identity))

  ([data template formats join]
   (join
     (for [expr template]
       (cond
         (is-var? expr) ((formats expr) (data expr))
         (fn? expr) (expr)
         (seq-vec? expr) (let [[t k & expr] expr
                               data (data k)]
                           (case t
                             :expr (gen data expr formats) 
                             :list (gen-list expr formats data)
                             :fn (data)
                             (format "** ERROR %s not recognized **" t)))
         :else expr)))))

(defn- gen-list
  [data template formats join]
  (join
    (for [datum data]
      (gen datum template formats))))
