(ns expert.views.home
  (:require [expert.routes :as routes]
            [secretary.core :as secretary :include-macros true]
            [reagent.session :as session]
            [accountant.core :as accountant]))

(defn active-convo-card
  [convo]
  [:div.card.active-conversation {:key (str "active-" (:id convo))}
    [:div.card-header "In Progress"]
    [:img.card-img-top {:src (:url (:expert convo))} ]
    [:div.card-block
      [:h4.card-title (:topic convo)]
      [:h6.card-subtitle.text-muted (:name (:expert convo))]
      [:p.card-text "Want to get answers to your questions? Pick a topic and start up a conversation with one of our experts."]
    ]
  ])
(defn upcoming-convo-card
  [convo]
  [:div.card {:key (str "upcoming-" (:id convo))}
   [:img.card-img-top {:src (:url (:expert convo))} ]
   [:div.card-block
     [:h4.card-title (:topic convo)]
     [:h6.card-subtitle.text-muted (:name (:expert convo))]
  ;   [:h3.text-muted "Starts at:" (:start-at convo)]
     [:p.card-text "This is an upcoming conversation"]
   ]
  ])

(defn archived-convo-card
  [convo]
  [:div.card ;{:key (str "archived-" (:id convo))}
    [:img.card-img-top {:src "http://placehold.it/640x480?text=Thumbnail+of+video"}]
    [:div.card-block
      [:h4.card-title (:topic convo)]
      [:h6.card-subtitle.text-muted (:name (:expert convo))]
      [:p.card-text
        "This was a conversation from the past, we'll keep the last ... 30 days or so. You can download them if you'd like"
      ]
    ]
  ])

(defn root []
    (let [active   (session/get-in [:user :conversations :active] [])
          upcoming (session/get-in [:user :conversations :upcoming] [])
          archived (session/get-in [:user :conversations :archived] [])
          ]
        [:section.container
          [:h1 "Conversations"]
          [:span (count active)]
          [:div.card-deck-wrapper
            [:div.card-deck
              (doall (map #(vector active-convo-card %) active))
            ]
          ]
          [:div.card-deck-wrapper
            [:div.card-deck
              (doall (map #(vector upcoming-convo-card %) upcoming))
            ]
          ]
          [:div.card-deck-wrapper
            [:div.card-deck
              (doall (map #(vector archived-convo-card %) archived))
            ]
          ]
      ])
   )
