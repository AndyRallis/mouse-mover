(ns mouse-mover.core 
  (:gen-class)
  (:import (java.awt Robot MouseInfo)))

(defonce TIMER 5000)


(defn grab-pointer
  []
  (let [point (.. MouseInfo getPointerInfo getLocation)]
    [(.getX point) (.getY point)]))

(defn set-pointer 
  [[new-x new-y]]
  (let [rob-the-robot (Robot.)]
    (.mouseMove rob-the-robot (int new-x) (int new-y))))

(defn random-xy 
  []
  (if (< (rand) 0.5)
     (* 2 (rand))
     (* -2 (rand))))

(defn new-xy
  [[cur-x cur-y]]
  [(+ cur-x (random-xy)) (+ cur-y (random-xy))])

(defn move-it-move-it
  []
  (while true
    (loop []
      (let [current-pointer (grab-pointer)
            new-xys (new-xy current-pointer)]
        (set-pointer new-xys)
        (Thread/sleep TIMER))))) 

(defn -main
  [& args]
  (move-it-move-it))
