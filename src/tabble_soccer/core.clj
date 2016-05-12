(ns tabble-soccer.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [req]
  (if (= "/hello" (:uri req))
    {:status 200
     :body "<h1> Hello world </h1>"
     :headers {}}
    {:status 404
     :body "<h1> Page not found </h1>"
     :headers {}}))

(def app handler)

(defn -main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
              

