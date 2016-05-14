(defproject table-soccer "0.1.0-SNAPSHOT"
  :description "Table soccer portal"
  :url "TBA"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.4.0"]
                 [hickory "0.6.0"]]

  :main table-soccer.core

  :profiles {:test {:dependencies [[ring/ring-mock "0.3.0"]]}})
