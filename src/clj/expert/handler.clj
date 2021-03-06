(ns expert.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [expert.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

(def mount-target
  [:div#app
      [:h3 "Loading..."]
      [:span.fa.fa-spin.fa-circle-o-notch.fa-3x.fa-fw]
      ])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css "/bootstrap/css/bootstrap.min.css")
   (include-js "https://use.fontawesome.com/97eead68fd.js")
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js") ]))


(defroutes routes
  (resources "/")
  (GET "*" [] (loading-page))
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
