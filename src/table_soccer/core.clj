(ns table-soccer.core
  (:use     [table-soccer.handler :refer [handle-root
                                          handle-about
                                          handle-not-found]]
            [table-soccer.player.handler :refer [handle-index-players]])
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.resource :refer [wrap-resource]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]))

(defroutes routes
  (GET "/" [] handle-root)
  (GET "/about" [] handle-about)
  (GET "/players" [] handle-index-players)
  (route/not-found handle-not-found))

(def app (wrap-resource routes "static"))

(defn -main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
