(ns xres.core
  (:require [lumo.core]
            [fs]
            [os]))

(def path (str (os/homedir) "/.Xresources"))

(def file (atom (str (fs/readFileSync path))))

(add-watch file :fs-sync (fn [_ _ _ data]
                           (fs/writeFileSync path data)))

(defn toggle-font [f]
  (clojure.string/replace
   f
   #"pixelsize=(\d+\d+)"
   (comp #(str "pixelsize=" %) {"24" "14" "14" "24"} second)))

(defn ^:export -main
  "Toggle pixelsize property in ~/.Xresources, between 24 and 14."
  [& args]
  (swap! file toggle-font))

(set! *main-cli-fn* -main)
