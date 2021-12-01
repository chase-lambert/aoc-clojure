(ns aoc.2015.day-02
  (:require
   [clojure.string :as string]))

(def input
  (slurp "resources/2015/day_02.txt"))

(defn create-box [s]
  (let [[length width height] (->> (string/split s #"x")
                                   (map parse-long))]
    {:length length
     :width  width
     :height height}))

(def boxes
  (->> input
       string/split-lines
       (map create-box)))

(defn surface-area [{:keys [length width height]}]
  (+ (* 2 length width) (* 2 width height) (* 2 height length)))

(defn area-smallest-side [{:keys [length width height]}]
  (let [[a b _] (sort [length width height])]
    (* a b)))

(defn required-wrapping-paper [box]
  (+ (surface-area box) (area-smallest-side box)))

(defn smallest-perimeter [{:keys [length width height]}]
  (let [[a b _] (sort [length width height])]
    (+ (* 2 a) (* 2 b))))

(defn volume [{:keys [length width height]}]
  (* length width height))

(defn ribbon-needed [box]
  (+ (smallest-perimeter box) (volume box)))

(defn part-1 [boxes]
  (apply + (map required-wrapping-paper boxes)))

(defn part-2 [boxes]
  (apply + (map ribbon-needed boxes)))

(comment
  (part-1 boxes) ;; 1598415
  (part-2 boxes) ;; 3812909
  )


