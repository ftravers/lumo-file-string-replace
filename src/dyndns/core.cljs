;; read file: ~/.Xresources and toggle the font between values
;; 24 and 14.

(ns dyndns.core
  (:require [lumo.core]
            [fs]
            [os]
            #_[clojure.string :as s]))

(def fname (str (os/homedir) "/.Xresources")) 
(def xres (str (fs/readFileSync fname)))
(defn wf [data]
  (fs/writeFileSync fname data))
(defn abx [found]
  (->> found
       second
       {"44" "14" "14" "44"}))

(def match-str #"pixelsize=(\d+\d+)")
(defn make-new-str []
  (clojure.string/replace
  xres match-str
  #(str "pixelsize=" (->
                      %
                      second
                      {"44" "14" "14" "44"}))))

(defn ^:export -main [& args] 
  (wf (make-new-str) ))
(set! *main-cli-fn* -main)
