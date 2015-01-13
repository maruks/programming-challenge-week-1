(ns programming-challenge-week-1.core-test
  (:require [clojure.test :refer :all]
            [programming-challenge-week-1.core :refer :all]))

(deftest test1
  (testing "solve function"
    (is (= "28" (solve "(2,4); M1=[1,2,3,11,12,4]; M2=[5,6,4]; M3=[1,6,7]; M4=[5,6,4]; M5=[8,6,3]")))

    (is (= "59" (solve "(1,7); M1=[1,2,3,4]; M2=[5,6,4]; M3=[9,6,7]; M4=[12,1,2,3,11,16,15,14,10,13,7]")))
    (is (= "73" (solve "(3,299); M1=[1,2,3,4]; M2=[6,7,19,12,4]; M3=[11,14,16,6]; M4=[24,299,42,6]")))
    (is (= "None" (solve "(3,4); M1=[1,2,3]; M2=[6,7,19,12,4]; M3=[11,14,16,6]")))

    ))

