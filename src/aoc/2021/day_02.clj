(ns aoc.2021.day-02
  (:require
   [clojure.string :as string]))

(def input
  (slurp "resources/2021/day_02.txt"))

(defn s->command [s]
  (let [[position units] (-> s (string/split #" "))
        units (parse-long units)]
    (case position
      "forward" [units, 0]
      "down"    [0, units]
      "up"      [0, (- units)])))

(def commands
  (->> input
       (string/split-lines)
       (map s->command)))

(defn part-1 [commands]
  (apply *
         (reduce (fn [current command]
                   (map + current command))
                 [0 0]
                 commands)))

(defn part-2 [commands]
  (loop [commands commands
         current [0 0]
         aim 0]
    (if (seq commands)
      (let [[x y] current
            [x-delta y-delta] (first commands)]
        (if (zero? x-delta)
          (recur (rest commands) current (+ aim y-delta))
          (recur (rest commands) [(+ x x-delta) (+ y (* aim x-delta))] aim)))
      (apply * current))))

(comment
  (part-1 commands) ;; 2070300
  (part-2 commands) ;; 2078985210
  ,)

