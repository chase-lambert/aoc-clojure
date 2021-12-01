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
  (let [windows (partition 3 1 measurements)]
    (count 
      (for [i (range 1 (count windows))
            :let [sum-a (apply + (nth windows (dec i)))
                  sum-b (apply + (nth windows i))]
            :when (> sum-b sum-a)]
         i))))
  
(comment
  (part-1 measurements) ;; 1696
  (part-2 measurements) ;; 1737
  ,)
