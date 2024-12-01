(ns aoc.2024.day-01
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2024/day_01.txt"))

(def data
  (->> input 
       (str/split-lines)
       (map #(str/split % #"\s+"))
       (map (partial map parse-long))))

(defn part-1 [data]
  (let [xs (sort (map first data))
        ys (sort (map second data))
        diffs (map abs (map - ys xs))]
    (reduce + 0 diffs)))

(defn part-2 [data]
  (let [xs (map first data)
        y-freqs (frequencies (map second data))]
    (reduce (fn [acc x]
              (let [freq-x (get y-freqs x 0)]
                (+ acc (* x freq-x))))
            0
            xs)))
          
(comment
  (part-1 data) ;; 2756096
  (part-2 data) ;; 23117829
  ,)

