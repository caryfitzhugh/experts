(ns expert.views.home
  (:require [expert.routes :as routes]
            [secretary.core :as secretary :include-macros true]
            [reagent.session :as session]
            [expert.layout :as layout]
            [cljs-time.format :as tf]
            [reagent.core :as r]
            [accountant.core :as accountant]))

(defn active-conversations
  []
  (let [active   (session/get-in [:user :conversations :active] [])]
    [:h1 "Active Conversations"]
    [:table.table
      [:thead.thead-inverse
        [:tr
          [:th "Topic"]
          [:th "Expert"]
          [:th "Started Time"]
          [:th ""]
        ]
      ]
      [:tbody
        (doall (map (fn [convo]
          [:tr {:key (str "upcoming-" (:id convo))}
            [:td  (:topic convo)]
            [:td  (:name (:expert convo))]
            [:td  (tf/unparse (tf/formatters :basic-date-time) (:start-at convo))]
            [:td
              [:a.btn.btn-primary.btn-block "Join"]
              [:a.btn.btn-danger.btn-block "Cancel"]
            ]
          ]) active))
      ]]))

(defn upcoming-conversations
  []
  (let [upcoming   (session/get-in [:user :conversations :upcoming] [])]
    [:h1 "Upcoming Conversations"]
    [:table.table
      [:thead.thead-inverse
        [:tr
          [:th "Topic"]
          [:th "Expert"]
          [:th "Start Time"]
          [:th ""]
        ]
      ]
      [:tbody
        (doall (map (fn [convo]
          [:tr {:key (str "upcoming-" (:id convo))}
            [:td  (:topic convo)]
            [:td  (:name (:expert convo))]
            [:td  (tf/unparse (tf/formatters :basic-date-time) (:start-at convo))]
            [:td
              [:a.btn.btn-primary.btn-block "Edit"]
              [:a.btn.btn-danger.btn-block "Cancel"]
            ]
          ]) upcoming))
      ]]))

(defn history-conversations
  []
  (let [history   (session/get-in [:user :conversations :history] [])]
    [:h1 "Conversation History"]
    [:table.table
      [:thead.thead-inverse
        [:tr
          [:th "Topic"]
          [:th "Expert"]
          [:th ""]
        ]
      ]
      [:tbody
        (doall (map (fn [convo]
          [:tr {:key (str "history-" (:id convo))}
            [:td  (:topic convo)]
            [:td  (:name (:expert convo))]
            [:td
              [:a.btn.btn-primary.btn-block "View"]
            ]
          ]) history))
      ]]))

(defn input-element
  "An input element which updates its value on change"
  [id name type value]
  [:input {:id id
           :name name
           :class "form-control"
           :type type
           :required ""
           :defaultValue (name @value)
           :on-change (fn [evt] (swap! value assoc name (-> evt .-target .-value)) )}])

(defn profile
  []
  (let [profile-data (r/atom (session/get-in [:user :profile]))]
    (fn []
      [:form
        [:div.form-group
          [:label "Name"]
          (input-element :name :name :text profile-data)
        ]
        [:div.form-group
          [:label "Email"]
          (input-element :email :email :email profile-data)
        ]
        [:div.form-group
          [:button.btn.btn-primary {:type :submit :on-click (fn [evt]
            (session/swap! assoc-in [:user :profile] @profile-data)
            (.preventDefault evt))}
            "Save"
          ]
        ]
    ])))

(defn root []
    (let [ selection (session/get-in [:views :home :selected]) ]
      [:div.container
        [:div.col-md-3 [layout/home-sidebar] ]
        [:div.col-md-9
          (cond
            (= selection :profile) [profile]
            (= selection :active) [active-conversations]
            (= selection :upcoming) [upcoming-conversations]
            (= selection :history) [history-conversations])] ]))
