(ns programming-challenge-week-1.core
  (:require [clojure.data.priority-map :refer [priority-map]]
            [clojure.string :refer [split]]
            [instaparse.core :as insta])
  (:gen-class))

(def weight-1 7)
(def weight-2 12)

(defn shortest-path [weights from]
  (loop [queue (assoc (apply priority-map (interleave (keys weights) (repeat Integer/MAX_VALUE))) from 0)
         result {}]
    (if (empty? queue)
      result
      (let [[[lnode ldist] unvisited] ((juxt peek pop) queue)
            updated (reduce (fn [q [n w]] (if (and (contains? q n) (< (+ ldist w) (q n))) (assoc q n (+ ldist w)) q)) unvisited (weights lnode))]
        (recur updated (assoc result lnode ldist))))))

(def parser (insta/parser (clojure.java.io/resource "input.bnf")))

(defn route [bus stops]
  (let [r (partition 2 1 (map (partial str bus "-") stops))
        ks (map first r)
        vs (map #(hash-map (second %) weight-1) r)]
    (zipmap ks vs)))

(defn solve [s]
  (let [[[_ from to] & ms] (parser s)
        rs (apply merge (map (fn [[_ b r]] (let [rs (split r #",")] (merge-with merge (route b rs) (route b (reverse rs))))) ms))
        stops (keys rs)
        graph (into {} (map (fn [[k v]]
                              [k (merge v (zipmap
                                           (filter #(let [[b1 s1] (split k #"-") [b2 s2] (split % #"-")] (and (= s1 s2) (not= b1 b2))) stops)
                                           (repeat weight-2)))])
                            rs))
        from-stops (filter #(= from (second (split % #"-"))) stops)
        paths (apply merge-with min (map (partial shortest-path graph) from-stops))
        result (reduce min (map second (filter #(= to (second (split (key %) #"-"))) paths)))]
    (if (= result Integer/MAX_VALUE) "None" (str result))))

(defn -main [& args]
  (doseq [i (line-seq (java.io.BufferedReader. *in*))]
    (println (solve i))))
