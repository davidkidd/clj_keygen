(require '[clojure.java.io :as io])

(defn get-key [chunk-size, key-length] 
  (apply str 
   (flatten
    (interpose "-" 
     (partition chunk-size 
      (take key-length 
       (repeatedly #(rand-nth "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"))))))))

(def keys (apply str (interpose "\n" (flatten (take 100000 (repeatedly #(get-key 5 25)))))))

(defn dump-keys [n] 
  (with-open [wrt (io/writer "keys.txt")]
    (doseq [i (range n)]
      (.write wrt (str (get-key 5 25) "\n")))))