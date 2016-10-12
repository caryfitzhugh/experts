(ns expert.routes
  (:require
              [secretary.core :as secretary :include-macros true]
              [reagent.session :as session]
              ))


;; -------------------------
;; Routes

(secretary/defroute welcome-path "/" []
  (session/put! :current-page :welcome-page))

(secretary/defroute get-help-path "/get-help" []
  (session/put! :current-page :get-help))

(secretary/defroute about-path "/about"  []
  (session/put! :current-page :about-page))

(secretary/defroute login-path "/user/login" []
  (session/put! :current-page :login))

(secretary/defroute home-path "/home"  []
  (session/put! :current-page :home-page))

(secretary/defroute "/*" []
  (session/put! :current-page :not-found-page))
