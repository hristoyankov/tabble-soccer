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
      (is (= "List of players" (-> (s/select (s/descendant (s/tag :body)
                                                         (s/tag :h1))
                                           body)
                                   first :content first string/trim)))
      
      (is (= "ID" (-> (s/select (s/descendant (s/tag :body)
                                              (s/tag :table)
                                              (s/tag :thead)
                                              (s/tag :tr)
                                              s/first-child)
                                body)
             first :content first string/trim)))
      (is (= "Name" (-> (s/select (s/descendant (s/tag :body)
                                                (s/tag :table)
                                                (s/tag :thead)
                                                (s/tag :tr)
                                                (s/nth-child 2))
                                  body)
                        first :content first string/trim)))

      ;; 1. John Doe 
      (is (= "1" (-> (s/select (s/descendant (s/tag :body)
                                             (s/tag :table)
                                             (s/tag :tbody)
                                             s/first-child
                                             s/first-child)
                               body)
                     first :content first string/trim)))
      (is (= "John Doe" (-> (s/select (s/descendant (s/tag :body)
                                                    (s/tag :table)
                                                    (s/tag :tbody)
                                                    s/first-child
                                                    (s/nth-child 2))
                                      body)
                            first :content first string/trim)))

      ;; 2. Samantha Lu
      (is (= "2" (-> (s/select (s/descendant (s/tag :body)
                                             (s/tag :table)
                                             (s/tag :tbody)
                                             (s/nth-child 2)
                                             s/first-child)
                               body)
                     first :content first string/trim)))
      (is (= "Samantha Lu" (-> (s/select (s/descendant (s/tag :body)
                                                       (s/tag :table)
                                                       (s/tag :tbody)
                                                       (s/nth-child 2)
                                                       (s/nth-child 2))
                                         body)
                               first :content first string/trim)))

      ;; 1. Gregory Hopps
      (is (= "3" (-> (s/select (s/descendant (s/tag :body)
                                             (s/tag :table)
                                             (s/tag :tbody)
                                             (s/nth-child 3)
                                             s/first-child)
                               body)
                     first :content first string/trim)))
      (is (= "Gregory Hopps" (-> (s/select (s/descendant (s/tag :body)
                                                         (s/tag :table)
                                                         (s/tag :tbody)
                                                         (s/nth-child 3)
                                                         (s/nth-child 2))
                                           body)
                                 first :content first string/trim))))))
