(ns aoc.2023.day-01
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2023/day_01.txt"))


(defn part-1 [input]
  (->> input
       (str/split-lines)
       (map #(re-seq #"\d" %))
       (map (juxt first last))
       (map (comp parse-long (partial apply str)))
       (reduce +)))


(def number-words
  ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])

(def word->num 
  (zipmap number-words (range 1 10)))

(def regex-pattern 
  (re-pattern (str "(?=(" (str/join "|" number-words) "|\\d))")))

(defn part-2 [input]
  (->> input
       (str/split-lines)
       (map #(re-seq regex-pattern %))
       (map (fn [matches]
              (map second matches)))
       (map (juxt first last))
       (map #(map (fn [x]
                    (if (= 1 (count x))
                      x
                      (word->num x)))
                  %))
       (map (comp parse-long (partial apply str)))
       (reduce +)))


(comment
  (part-1 input) ;; 53080
  (part-2 input) ;; 53268
  ,)
