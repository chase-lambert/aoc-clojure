(ns aoc.2023.day-01
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2023/day_01.txt"))

(def data
  (str/split-lines input))


(defn part-1 [data]
  (->> data
       (map #(re-seq #"\d" %))
       (map (juxt first last))
       (map (comp parse-long (partial apply str)))
       (reduce +)))

(defn part-1-transduce [data]
  (let [xform (comp 
                (map #(re-seq #"\d" %))
                (map (juxt first last))
                (map (comp parse-long (partial apply str))))]
    (transduce xform + data)))


(def number-words
  ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine"])

(def word->num 
  (zipmap number-words (range 1 10)))

(def regex-pattern 
  (re-pattern (str "(?=(" (str/join "|" number-words) "|\\d))")))

(defn part-2 [data]
  (->> data
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
  (part-1 data) ;; 53080
  (part-2 data) ;; 53268
  (part-1-transduce data) ;; 53080
  ,)
