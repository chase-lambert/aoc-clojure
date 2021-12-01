(ns aoc.2015.day-05
  (:require
   [clojure.string :as string]))

(def input
  (slurp "resources/2015/day_05.txt"))

(def strings
  (string/split-lines input))

(defn three-vowels? [s]
  (let [vowels #{\a \e \i \o \u}
        s-vowels (filter #(vowels %) s)]
    (>= (count s-vowels) 3)))

(defn double-letters? [s]
  (not-empty
   (for [i (range 1 (count s))
         :when (= (nth s i) (nth s (dec i)))]
     i)))

(defn no-forbidden-strings? [s]
  (let [forbidden ["ab" "cd" "pq" "xy"]]
    (not
     (some #(string/includes? s %) forbidden))))

(defn nice-string? [s]
  (and (three-vowels? s) (double-letters? s) (no-forbidden-strings? s)))

(defn part-1 [strings]
  (count
   (filter nice-string? strings)))

(defn part-2 [strings])

(comment
  (part-1 strings) ;; 255
  (part-2 strings))

