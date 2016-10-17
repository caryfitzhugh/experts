(ns expert.routes
  (:require
              [secretary.core :as secretary :include-macros true]
              [expert.controllers.auth :as auth-controller]
              [expert.paths :as paths]
              [accountant.core :as accountant]
              [reagent.session :as session]
              ))

;; -------------------------
;; Routes

(secretary/defroute (paths/welcome-path) []
  (session/put! :current-page :welcome-page))

(secretary/defroute (paths/get-help-path) []
  (session/put! :current-page :get-help))

(secretary/defroute (paths/about-path) []
  (session/put! :current-page :about-page))

(secretary/defroute (paths/login-path) []
  (session/put! :current-page :login)
  (auth-controller/login))

;; HOME / CONVERSATIONS
(secretary/defroute (paths/home-path) []
    (accountant/navigate! (paths/home-active-conversations-path)))

(secretary/defroute (paths/home-active-conversations-path) []
    (session/assoc-in! [:views :home :selected] :active)
    (session/put! :current-page :home-page))

(secretary/defroute (paths/home-upcoming-conversations-path) []
    (session/assoc-in! [:views :home :selected] :upcoming)
    (session/put! :current-page :home-page))

(secretary/defroute (paths/home-history-conversations-path) []
    (session/assoc-in! [:views :home :selected] :history)
    (session/put! :current-page :home-page))

(secretary/defroute (paths/home-profile-path) []
    (session/assoc-in! [:views :home :selected] :profile)
    (session/put! :current-page :home-page))


(secretary/defroute "/*" []
  (session/put! :current-page :not-found-page))
