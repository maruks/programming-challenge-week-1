(defproject programming-challenge-week-1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [instaparse "1.3.5"]
                 [org.clojure/data.priority-map "0.0.5"]]
  :repl-options {:port 2323}
  :main ^:skip-aot programming-challenge-week-1.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
