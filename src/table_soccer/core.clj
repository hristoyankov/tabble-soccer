(ns table-soccer.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]))

(defroutes app
  (GET "/" [] {:status 200
               :body "<h1> Hello user </h1>"
               :headers {}})
  (GET "/about" [] {:status 200
                    :body "<h1> About table soccer. </h1>"
                    :headers {}})
  (GET "/players" [] {:status 200
                      :body "empty"
                      :headers {}})
  (route/not-found {:status 404
                    :body "<h1> Page not found </h1>"
                    :headers {}}))

(defn -main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
