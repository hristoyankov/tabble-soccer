(ns tabble-soccer.core-test
  (:require [clojure.test :refer :all]
            [tabble-soccer.core :refer :all]
            [ring.mock.request :refer :all]))

(deftest routes-test
  (testing "Homepage"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (re-matches #".*<h1>.*Hello user.*</h1>.*" (:body response)))))
  (testing "About page"
    (let [response (app (request :get "/about"))]
      (is (= (:status response) 200))
      (is (re-matches #".*<h1>.*About table soccer.*</h1>.*" (:body response)))))
  (testing "Page not found"
    (let [response (app (request :get "/bogus_route"))]
      (is (= (:status response) 404)))))
