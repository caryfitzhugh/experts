(ns expert.views.auth
  (:require [expert.routes :as routes]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]))

(defn login
  []
  [:section.container
    [:div.form
      [:div {:class :form-group}
        [:input {:type :email :placeholder "Email Address"}]
      ]
      [:div {:class :form-group}
        [:input {:type :password :placeholder "Password"}]
      ]
      [:div {:class :form-group}
        [:input {:type :submit :value :Login :on-click #(accountant/navigate! (routes/home-path)) }]
      ]
    ]
  ]
  )
