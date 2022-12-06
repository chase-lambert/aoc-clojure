(ns aoc.2022.day-02
  (:require 
    [clojure.string :as str]))

(def input
  (slurp "resources/2022/day_02.txt"))

(def data
  (->> input
       (str/split-lines)))

(def shape->points
  {\X 1
   \Y 2
   \Z 3})

(defn outcome->points [comp-move player-move]
  (let [round [comp-move player-move]]
    (cond
      (contains? #{[\A \X]
                   [\B \Y]
                   [\C \Z]}
                 round) 
      3
      
      (contains? #{[\A \Z]
                   [\B \X]
                   [\C \Y]}
                 round)
      0
      
      :else 6)))  

(defn ->player-move [comp-move result-needed]
  (let [round [comp-move result-needed]]
    (case round 
      [\A \X] \Z
      [\A \Y] \X
      [\A \Z] \Y
      [\B \X] \X
      [\B \Y] \Y
      [\B \Z] \Z
      [\C \X] \Y
      [\C \Y] \Z
      [\C \Z] \X)))

(defn part-1 [data]
  (let [comp-moves     (map first data)
        player-moves   (map last  data)
        shape-points   (reduce + (map shape->points player-moves))
        outcome-points (reduce + (map outcome->points comp-moves player-moves))]
    (+ shape-points outcome-points)))

(defn part-2 [data]
  (let [comp-moves     (map first data)
        results-needed (map last  data) 
        player-moves   (map ->player-move comp-moves results-needed)
        shape-points   (reduce + (map shape->points player-moves))
        outcome-points (reduce + (map outcome->points comp-moves player-moves))]
    (+ shape-points outcome-points)))

(comment
  (part-1 data) ;; 15691
  (part-2 data) ;; 12989
  ,)

