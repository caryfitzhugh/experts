(ns expert.views.auth
  (:require [expert.routes :as routes]
            [secretary.core :as secretary :include-macros true]
            [expert.controllers.auth :as controller]
            [expert.views.helpers :as h]
            [reagent.core :as r]
            [accountant.core :as accountant]))

(defn login
  []
  (let [login-data (r/atom {})]
    (fn []
      [:section.container
        [:div.form
          [:div {:class :form-group}
            (h/input-element :email :email :email login-data
                    :placeholder "Enter your email address")
          ]
          [:div {:class :form-group}
            (h/input-element :password :password :password login-data
                    :placeholder "Password")
          ]
          [:div {:class :form-group}
            [:input {:type :submit :value :Login :on-click #(controller/do-login @login-data)}]
          ]
        ]
      ])))
