(ns aoc.core
  (:require [criterium.core :refer [bench]]))


(comment 
  (bench (aoc.2015.day-01/part-1 aoc.2015.day-01/directions))
  (bench (aoc.2015.day-01/part-2 aoc.2015.day-01/directions))

  (bench (aoc.2023.day-01/part-1 aoc.2023.day-01/data))
  (bench (aoc.2023.day-01/part-1-transduce aoc.2023.day-01/data))
  (bench (aoc.2023.day-01/part-2 aoc.2023.day-01/data)))

(defn -main [& _args])



