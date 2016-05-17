(ns table-soccer.player.view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn players-page [players]
  (html5 {:lang :en}
         [:head
          [:title "Players page"]]
         [:body
          [:div
           [:h1 "List of players"]
           (if (seq players)
             [:table
              [:thead
               [:tr
                [:th "ID"]
                [:th "Name"]]]
              [:tbody
               (for [p players]
                 [:tr
                  [:td (:id p)]
                  [:td (:name p)]])]])]]))
          
           
