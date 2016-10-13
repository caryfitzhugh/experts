(ns expert.layout
  (:require [expert.routes :as routes]
            [reagent.session :as session]
            [accountant.core :as accountant]
            ))

(defn layout [content]
  [:div.body
    [:nav.navbar.navbar-static-top.navbar-dark.bg-primary
      [:div.container
        [:div.navbar-header
          [:a.navbar-brand {:href (routes/welcome-path)} "TheGoToExperts"]
        ]
      ]
    ]
    content
  ]
)
(defn landing-layout [content]
  [:div.body
    [:nav.navbar.navbar-static-top.navbar-dark.bg-primary
      [:div.container
        [:div.navbar-header
          [:a.navbar-brand {:href (routes/welcome-path)} "TheGoToExperts"]
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
        [:a.nav-link {:on-click #(accountant/navigate! (routes/home-active-conversations-path))
                      :class (when (= selection :active) "active")} "Active"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (routes/home-upcoming-conversations-path))
                      :class (when (= selection :upcoming) "active")} "Upcoming"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (routes/home-history-conversations-path))
                      :class (when (= selection :history) "active")} "History"]
      ]
      [:li.nav-item
        [:a.nav-link {:on-click #(accountant/navigate! (routes/home-profile-path))
                      :class (when (= selection :profile) "active")} "Profile"]
      ]
    ]))
