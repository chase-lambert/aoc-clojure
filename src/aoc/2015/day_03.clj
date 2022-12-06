(ns aoc.2015.day-03
  (:require
   [clojure.set :as set]))

(def input
  (slurp "resources/2015/day_03.txt"))

(def direction->move
  {\^ [1, 0]
   \v [-1, 0]
   \> [0, 1]
   \< [0, -1]})

(def moves
  (map direction->move input))

(defn houses-visited [moves]
  (loop [moves moves
         current [0 0]
         visited #{current}]
    (if (seq moves)
      (let [[x y] current
            [x-delta y-delta] (first moves)
            next-house [(+ x x-delta) (+ y y-delta)]]
        (recur (rest moves) next-house (conj visited next-house)))
      visited)))

(defn part-1 [moves]
  (count
   (houses-visited moves)))

(defn part-2 [moves]
  (let [santa-moves (take-nth 2 moves)
        robot-moves (take-nth 2 (rest moves))
        santa-houses-visited (houses-visited santa-moves)
        robot-houses-visited (houses-visited robot-moves)]
    (count
     (set/union santa-houses-visited robot-houses-visited))))

(comment
  (part-1 moves) ;; 2592
  (part-2 moves) ;; 2360
  ,)
  



