(ns table-soccer.handler)

(def handle-root
  {:status 200
   :body "<h1> Hello user </h1>"
   :headers {}})

(def handle-about
  {:status 200
   :body "<h1> About table soccer. </h1>"
   :headers {}})

(def handle-not-found
  {:status 404
   :body "<h1> Page not found </h1>"
   :headers {}})
