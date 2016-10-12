(ns expert.views
(:require
              [expert.layout :as layout]
              [expert.routes :as routes]
              [reagent.session :as session]
  ))

(def views
  {:about-page
    (layout/layout
      [:div.container
        [:h2 "About expert"]
        [:div [:a {:href "/"} "go to the home page"]]])

  :not-found
    (layout/layout
      [:div.container
        [:h2 "Not Found!"]
        [:div [:a {:href "/"} "go to a happier place..."]]])

  :welcome-page
    (layout/landing-layout
      [:div
        [:div.container.landing-header
          [:h2 "The Go To Experts"]
          [:div [:a.btn.btn-lg.btn-primary {:href (routes/get-help-path)} "Get Help"]]
        ]
        [:section.container
          [:h3 "Get Help From Experts"]
          [:div
            [:p "Get Help from experts anytime anywhere.  On just about every subject we can help you get the 1 on 1 answers you need"]
          ]
        ]
      ])
  })

(defn current-page []
  [:div (get views (session/get :current-page))])
