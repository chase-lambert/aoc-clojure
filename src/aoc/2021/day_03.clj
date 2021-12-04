(ns aoc.2021.day-03 
  (:require
    [clojure.string :as string]))

(def input
  (slurp "resources/2021/day_03.txt"))

(def binary-strs
  (->> input
       (string/split-lines)))

(defn binary->decimal [bin-str]
  (Integer/parseInt bin-str 2))

(defn most-common-bits [bin-strs]
  (let [freqs (frequencies bin-strs)]
    (if (= (count freqs) 1)
      (let [value (ffirst freqs)]
        [value value])
      (let [[least most] (sort-by val freqs)
            least-common-bit (first least)
            most-common-bit (first most)]
        (if (= (val (first freqs)) (val (second freqs)))
          [\0 \1]
          [least-common-bit most-common-bit])))))

(defn gamma-episilon [binary-strs]
  (loop [nums binary-strs
         gamma []
         epsilon []]
    (if (seq (first nums))
      (let [b-strs (map first nums)
            [epsilon-bit gamma-bit] (most-common-bits b-strs)]
        (recur (map rest nums) (conj gamma gamma-bit) (conj epsilon epsilon-bit)))
      {:gamma   (binary->decimal (apply str gamma))
       :epsilon (binary->decimal (apply str epsilon))}))) 

(defn oxygen [binary-strs]
  (loop [nums binary-strs
         saved-oxygen binary-strs
         index 0]
    (if (> (count saved-oxygen) 1)
      (let [b-strs (map first nums)
            [_ most] (most-common-bits b-strs)
            kept-oxygen-nums (filter #(= most (nth % index)) saved-oxygen)]
        (recur (map #(drop (inc index) %) kept-oxygen-nums) kept-oxygen-nums (inc index)))
      (binary->decimal (first saved-oxygen)))))

(defn co2 [binary-strs]
  (loop [nums binary-strs
         saved-co2 binary-strs
         index 0]
    (if (> (count saved-co2) 1)
      (let [b-strs (map first nums)
            [least _] (most-common-bits b-strs)
            kept-co2-nums (filter #(= least (nth % index)) saved-co2)]
        (recur (map #(drop (inc index) %) kept-co2-nums) kept-co2-nums (inc index)))
      (binary->decimal (first saved-co2)))))

(defn part-1 [binary-strs]
  (let [{:keys [gamma epsilon]} (gamma-episilon binary-strs)]
    (* gamma epsilon)))

(defn part-2 [binary-strs]
  (let [oxygen (oxygen binary-strs)
        co2    (co2 binary-strs)]
    (* oxygen co2)))

(comment 
  (part-1 binary-strs) ;; 3242606
  (part-2 binary-strs) ;; 4856080
  ,)

