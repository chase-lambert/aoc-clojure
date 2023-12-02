(ns aoc.2023.day-02
  (:require
   [clojure.string :as str]))

(def input
  (slurp "resources/2023/day_02.txt"))

(def data
  (str/split-lines input))


(defn round-to-map [round]
  (let [sections (map str/trim (str/split round #","))]
    (apply merge 
           (map (fn [section] 
                  (let [[amount color] (str/split section #" ")
                        color  (keyword color)
                        amount (parse-long amount)]
                    {color amount}))
                sections))))

(defn parse-game [game]
  (let [[game rounds] (str/split game #":")
        id (parse-long (first (re-seq #"\d+" game)))
        rounds (re-seq #"[^;]+" rounds)
        round-map (mapv round-to-map rounds)] 
    [id round-map]))

(defn possible-round? [{:keys [red green blue] :or {red 0
                                                    green 0
                                                    blue 0}}
                       bag]
  (let [{:keys [total-red total-blue total-green]} bag]
    (and (>= total-red red)
         (>= total-green green)
         (>= total-blue blue))))

(defn part-1 [data bag]
  (let [games (->> data
                   (mapv parse-game))]
    (reduce + 0
      (for [[id rounds] games
            :when (every? #(possible-round? % bag) rounds)]
        id))))


(defn power [game]
  (let [minimums (for [color [:red :green :blue]
                       :let  [max-seen (map (fn [round]
                                              (or (color round) 0)) 
                                            game)]]
                   (apply max max-seen))]
    (reduce * 1 minimums)))

(defn part-2 [data]
  (let [games (->> data
                   (mapv parse-game)
                   (mapv second))]
    (reduce + (map power games))))

      
(comment
  (def test-input
    "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
  Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
  Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
  Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
  Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

  (def test-data
    (str/split-lines test-input))

  (def bag
    {:total-red 12
     :total-green 13
     :total-blue 14})

  (part-1 test-data bag) ;; 8
  (part-2 test-data) ;; 2286

  (part-1 data bag) ;; 2416
  (part-2 data) ;; 63307
  ,)
