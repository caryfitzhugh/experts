(ns expert.layout
  (:require [expert.paths :as paths]
            [reagent.session :as session]
            [expert.controllers.auth :as auth-controller]
            [accountant.core :as accountant]
            ))

(defn layout [content]
  (let [username (session/get-in [:user :profile :name])]
    [:div.body
      [:nav.navbar.navbar-static-top.navbar-dark.bg-primary
        (when username
          [:div.pull-xs-right
            [:span (str "Hello " username)]
            [:span.fa.fa-sign-out {:on-click (fn [evt] (auth-controller/do-logout))}]
          ])
        [:div.container
          [:div.navbar-header
            [:a.navbar-brand {:href (paths/welcome-path)} "TheGoToExperts"]
          ]
        ]
      ]
    content
  ]
))

(defn landing-layout [content]
  [:div.body
    [:nav.navbar.navbar-static-top.navbar-dark.bg-primary
      [:div.container
        [:div.navbar-header
          [:a.navbar-brand {:href (paths/welcome-path)} "TheGoToExperts"]
        ]
      ]
    ]
    content
  ]
)
(defn home-sidebar
  []
  (let [selection (session/get-in [:views :home :selected])]
    [:ul.nav.nav-pills.nav-stacked
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (paths/home-active-conversations-path))
                      :class (when (= selection :active) "active")} "Active"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (paths/home-upcoming-conversations-path))
                      :class (when (= selection :upcoming) "active")} "Upcoming"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (paths/home-history-conversations-path))
                      :class (when (= selection :history) "active")} "History"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (paths/home-profile-path))
                      :class (when (= selection :profile) "active")} "Profile"]
      ]
    ]))
