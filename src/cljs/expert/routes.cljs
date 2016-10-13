(ns expert.routes
  (:require
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
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

;; HOME / CONVERSATIONS
(secretary/defroute home-active-conversations-path "/home/conversations/active"  []
    (session/assoc-in! [:views :home :selected] :active)
    (session/put! :current-page :home-page))

(secretary/defroute home-upcoming-conversations-path "/home/conversations/upcoming"  []
    (session/assoc-in! [:views :home :selected] :upcoming)
    (session/put! :current-page :home-page))

(secretary/defroute home-history-conversations-path "/home/conversations/history"  []
    (session/assoc-in! [:views :home :selected] :history)
    (session/put! :current-page :home-page))

(secretary/defroute home-profile-path "/home/profile"  []
    (session/assoc-in! [:views :home :selected] :profile)
    (session/put! :current-page :home-page))

(secretary/defroute home-path "/home"  []
    (accountant/navigate! (home-active-conversations-path)))

(secretary/defroute "/*" []
  (session/put! :current-page :not-found-page))
