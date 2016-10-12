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
  (session/put! :current-page :welcome-page))

(secretary/defroute about-path "/about"  []
  (session/put! :current-page :about-page))

(secretary/defroute "/*" []
  (session/put! :current-page :not-found-page))
