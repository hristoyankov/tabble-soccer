(ns table-soccer.player.handler
  (:use [table-soccer.player.view :refer [players-page]]))

(def players [{:id 1
               :name "John Doe"}
              {:id 2
               :name "Samantha Lu"}
              {:id 3
               :name "Gregory Hopps"}])

(defn handle-index-players [req]
  {:status 200
   :headers {}
   :body (players-page players)})
