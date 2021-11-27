(ns aoc.2015.day-01)

(def input 
  (slurp "resources/2015/day_01.txt"))

(def data
  (map {\( 1, \) -1} input))

(defn part-1 [data]
  (apply + data))

(defn part-2 [data]
  (let [running-total (reductions + data)]
    (inc
     (.indexOf running-total -1))))

(comment
  (part-1 data) ;; 74
  (part-2 data) ;; 1795
  ,) 
  
