(ns expert.views.home
  (:require [expert.routes :as routes]
            [secretary.core :as secretary :include-macros true]
            [reagent.session :as session]
            [accountant.core :as accountant]))

(defn active-convo-card
  [convo]
  )

(defn archived-convo-card
  [convo]
  [:div.card {:key (str "archived-" (:id convo))}
    [:img.card-img-top {:src "http://placehold.it/640x480?text=Thumbnail+of+video"}]
    [:div.card-block
      [:h4.card-title (:topic convo)]
      [:h6.card-subtitle.text-muted (:name (:expert convo))]
      [:p.card-text
        "This was a conversation from the past, we'll keep the last ... 30 days or so. You can download them if you'd like"
      ]
    ]
  ])
(defn sidebar
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
    ]))

(defn active-conversations
  []
  (let [active   (session/get-in [:user :conversations :active] [])]
    [:h1 "Active Conversations"]
    [:div.card-deck-wrapper
      [:div.card-deck
        (doall (map (fn [convo]
          [:div.card.active-conversation {:key (str "active-" (:id convo))}
            [:div.card-header "In Progress"]
            [:img.card-img-top {:src (:url (:expert convo))} ]
            [:div.card-block
              [:h4.card-title (:topic convo)]
              [:h6.card-subtitle.text-muted (:name (:expert convo))]
              [:p.card-text "Want to get answers to your questions? Pick a topic and start up a conversation with one of our experts."]
            ]
          ]) active)) ]]))

(defn upcoming-conversations
  []
  (let [upcoming   (session/get-in [:user :conversations :upcoming] [])]
    [:h1 "Upcoming Conversations"]
    [:div.card-deck-wrapper
      [:div.card-deck
        (doall (map (fn [convo]
          [:div.card {:key (str "upcoming-" (:id convo))}
            [:img.card-img-top {:src (:url (:expert convo))} ]
            [:div.card-block
              [:h4.card-title (:topic convo)]
              [:h6.card-subtitle.text-muted (:name (:expert convo))]
            ;   [:h3.text-muted "Starts at:" (:start-at convo)]
              [:p.card-text "This is an upcoming conversation"]
            ]
                ]) upcoming))]]))

(defn history-conversations
  []
  (let [history   (session/get-in [:user :conversations :history] [])]
    [:h1 "Conversation History"]
    [:div.card-deck-wrapper
      [:div.card-deck
        (doall (map (fn [convo]
          [:div.card {:key (str "archived-" (:id convo))}
            [:img.card-img-top {:src "http://placehold.it/640x480?text=Thumbnail+of+video"}]
            [:div.card-block
              [:h4.card-title (:topic convo)]
              [:h6.card-subtitle.text-muted (:name (:expert convo))]
              [:p.card-text
                "This was a conversation from the past, we'll keep the last ... 30 days or so. You can download them if you'd like"
              ]]]) history))]]))

(defn root []
    (let [ selection (session/get-in [:views :home :selected]) ]
      [:section.container
        [:div.col-md-3 [sidebar] ]
        [:div.col-md-9
          (cond
            (= selection :active) [active-conversations]
            (= selection :upcoming) [upcoming-conversations]
            (= selection :history) [history-conversations])] ]))
