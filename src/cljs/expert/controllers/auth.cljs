(ns expert.controllers.auth
  (:require
              [expert.paths :as paths]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [reagent.session :as session]
              ))

(defn login
  []
  (let [profile (session/get-in [:user :profile])]
    (if profile
      (accountant/navigate! (paths/home-path)))))

(defn do-logout
  []
    (session/assoc-in! [:user :profile] nil)
    (accountant/navigate! (paths/welcome-path)))

(defn do-login
  [{email :email password :password}]
    (session/assoc-in! [:user :profile]
      {
        :name (str email)
        :avatar (str "https://api.adorable.io/avatars/285/CaryFitzHugh%40adorable.io")
        :email password
      })
    (login))
