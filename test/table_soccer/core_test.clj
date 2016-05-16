(ns table-soccer.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [table-soccer.core :refer :all]
            [ring.mock.request :refer :all]
            [hickory.core :refer [parse as-hickory]]
            [hickory.select :as s]))

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
      (is (= (:status response) 404))))

  (testing "Players"
    (let [response (app (request :get "/players"))
          body (-> response :body parse as-hickory)]
      (is (= (:status response) 200))
      ;; TODO Wrap in smaller functions to make it more readable
      (is (= "Players page" (-> (s/select (s/child (s/tag :head)
                                               (s/tag :title))
                                          body)
                                first :content first string/trim)))
      (is (= "Hello players" (-> (s/select (s/descendant (s/tag :body)
                                                         (s/tag :h1))
                                           body)
                                 first :content first string/trim))))))
