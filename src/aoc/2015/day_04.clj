(ns aoc.2015.day-04
  (:require
   [clojure.string :as string]
   [clj-commons.digest :as digest]))

(def input
  (string/trim-newline
   (slurp "resources/2015/day_04.txt")))

(defn find-md5-hash [secret-key desired-leading-zeroes]
  (first
   (for [i (range)
         :let [k (str secret-key i)
               digest (digest/md5 k)
               target (apply str
                             (repeat desired-leading-zeroes \0))]
         :when (string/starts-with? digest target)]
     i)))

(defn part-1 [secret-key]
  (find-md5-hash secret-key 5))

(defn part-2 [secret-key]
  (find-md5-hash secret-key 6))

(comment
  (part-1 input) ;; 117946
  (part-2 input) ;; 3938038
  (time (part-2 input)) ;; 3938038
  ,) 
  


