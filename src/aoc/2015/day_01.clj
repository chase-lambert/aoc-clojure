(ns aoc.2015.day-01)

(def input
  (slurp "resources/2015/day_01.txt"))

(def directions
  (map {\( 1, \) -1} input))

(defn part-1 [directions]
  (apply + directions))

(defn part-2 [directions]
  (let [current-floor (reductions + directions)]
    (inc
     (.indexOf current-floor -1))))

(comment
  (part-1 directions) ;; 74
  (part-2 directions)) ;; 1795
  

