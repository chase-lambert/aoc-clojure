(ns aoc.2022.day-01
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2022/day_01.txt"))

(def data
  (->> input
       (str/split-lines)
       (map parse-long)))

(defn sort-data [data]
  (->> data
       (partition-by nil?)
       (remove #(nil? (first %)))
       (map (partial apply +))
       sort
       reverse))

(defn part-1 [data]
  (->> data
       sort-data
       first))

(defn part-2 [data]
  (->> data
       sort-data
       (take 3)
       (apply +)))

(comment
  (part-1 data) ;; 70116
  (part-2 data) ;; 206582
  ,)
