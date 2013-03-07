(ns com.creeaaakk.tools.core)

(defn make-updater
  ([start update]
   (make-updater start update identity))

  ([start update view]
   (let [value (atom start)]
     (fn []
       (let [prev @value]
         (swap! value update)
         (view prev))))))

(defn make-counter
  [start]
  (make-updater start inc))
