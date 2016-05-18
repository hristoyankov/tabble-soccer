(ns table-soccer.player.view
  (:require [hiccup.core :refer [html h]]
            [hiccup.page :refer [html5]]))

(defn players-page [players]
  (html5 {:lang :en}
         [:head
          [:title "Players page"]
          [:meta {:name :viewport
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "bootstrap/css/bootstrap.min.css"
                  :rel :stylesheet}]]
         [:body
          [:div.container
           [:div.row
            [:div.col-sm-12
             [:h1 "List of players"]]]
           [:div.table-responsive
            (if (seq players)
              [:table.table.table-striped.table-hover
               [:thead
                [:tr
                 [:th "ID"]
                 [:th "Name"]]]
               [:tbody
                (for [p players]
                  [:tr
                   [:td (:id p)]
                   [:td (:name p)]])]])]]]
         [:script {:src "https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"}]
         [:script {:src "bootstrap/js/bootstrap.min.js"}]))
          
           
