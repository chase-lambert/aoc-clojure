(ns aoc.2021.day-01
  (:require
   [clojure.string :as string]))

(def input
  (slurp "resources/2021/day_01.txt"))

(def measurements
  (->> input
       (string/split-lines)
       (map parse-long)))

(defn part-1 [measurements]
  (->> (partition 2 1 measurements)
       (filter (fn [[a b]]
                 (> b a)))
       count))

(defn part-2 [measurements]
  (->> (partition 3 1 measurements)
       (map #(apply + %))
       part-1))

(comment
  (part-1 measurements) ;; 1696
  (part-2 measurements) ;; 1737
  ,)

