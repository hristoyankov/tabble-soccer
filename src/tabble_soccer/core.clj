(ns tabble-soccer.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [req]
  (let [path (:uri req)]
    (cond
      (= "/" path)
      {:status 200
       :body "<h1> Hello user </h1>"
       :headers {}}

      (= "/about" path)
      {:status 200
       :body "<h1> About table soccer. </h1>"
       :headers {}}

      :else
      {:status 404
       :body "<h1> Page not found </h1>"
       :headers {}})))

(def app handler)

(defn -main [port]
  (jetty/run-jetty (wrap-reload #'app) {:port (Integer. port)}))
              

