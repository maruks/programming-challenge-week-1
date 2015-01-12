(ns programming-challenge-week-1.core
  (:require [clojure.data.priority-map :refer [priority-map]]
            [instaparse.core :as insta])
  (:gen-class))

;; (2,4); M1=[1,2,3,11,12,4]; M2=[5,6,4]; M3=[1,6,7]; M4=[5,6,4]; M5=[8,6,3]
;; (1,7); M1=[1,2,3,4]; M2=[5,6,4]; M3=[9,6,7]; M4=[12,1,2,3,11,16,15,14,10,13,7]
;; (3,299); M1=[1,2,3,4]; M2=[6,7,19,12,4]; M3=[11,14,16,6]; M4=[24,299,42,6]
;; (3,4); M1=[1,2,3]; M2=[6,7,19,12,4]; M3=[11,14,16,6]

;;(def input (line-seq (java.io.BufferedReader. *in*)))

(def weight-1 7)
(def weight-2 12)

(def weights {"A" {"B" 4 "C" 3 "E" 7}
              "B" {"C" 6 "D" 5}
              "C" {"B" 6 "D" 11 "E" 8}
              "D" {"E" 2 "F" 2 "G" 10}
              "E" {"C" 8 "D" 2 "G" 5}
              "F" {"D" 2 "G" 3}
              "H" {"A" 1}
              "G" {"E" 5 "D" 10 "F" 3}})

(def input "(3,299); M1=[1,2,3,4]; M2=[6,7,19,12,4]; M3=[11,14,16,6]; M4=[24,299,42,6]")

(def parser (insta/parser (clojure.java.io/resource "input.bnf")))

(defn solve [p & ms])

(defn shortest-path [weights from]
  (loop [queue (assoc (apply priority-map (interleave (keys weights) (repeat Integer/MAX_VALUE))) from 0)
         result {}]
    (if (empty? queue)
      result
      (let [[[lnode ldist] unvisited] ((juxt peek pop) queue)
            updated (reduce (fn [q [n w]] (if (and (contains? q n) (< (+ ldist w) (q n))) (assoc q n (+ ldist w)) q)) unvisited (weights lnode))]
        (recur updated (assoc result lnode ldist))))))

(defn -main [& args]
  (println "Hello, World!"))

