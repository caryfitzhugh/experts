(ns expert.layout
  (:require [expert.routes :as routes]
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
