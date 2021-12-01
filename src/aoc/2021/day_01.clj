(ns aoc.2021.day-01 
  (:require
    [clojure.string :as string]))

(def input
  (slurp "resources/2021/input.txt"))

(def measurements
  (->> input
       (string/split-lines)
       (map parse-long)))

(defn part-1 [measurements]
  (count 
    (for [i (range 1 (count measurements))
          :when (> (nth measurements i) (nth measurements (dec i)))]
       i)))

(defn part-2 [measurements]
  (let [parted (partition 3 1 measurements)]
    (count (for [i (range 1 (count parted))
                 :when (> (apply + (nth parted i)) (apply + (nth parted (dec i))))]
              i))))
  
(comment
  (part-1 measurements)
  (part-2 measurements))
