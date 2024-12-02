(ns aoc.2024.day-02
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2024/day_02.txt"))

(def data
  (->> input 
       (str/split-lines)
       (map #(str/split % #"\s+"))
       (map (partial map parse-long))))

(defn safe? [report]
  (and (or (apply < report)
           (apply > report))
       (reduce (fn [a b]
                 (let [diff (abs (- b a))]
                   (if (or (> diff 3) 
                           (< diff 1))
                     (reduced false)
                     b)))
               (first report)
               (rest report))))

(defn part-1 [data]
  (count (filter safe? data)))

(comment
  
  (part-1 data) ;; 598
  
  ,)
  
(comment 
  (def a [7 6 4 2 1])
  (def b [1 2 7 8 9])

  (count (filter safe? [a b]))


  
  ,)

