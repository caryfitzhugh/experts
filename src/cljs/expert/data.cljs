(ns expert.data
  (:require
    [cljs-uuid-utils.core :as uuid]
    [cljs-time.core :as tc]
    [reagent.session :as session]
))

(defn expert
  [avatar]
    {:id (uuid/uuid-string (uuid/make-random-uuid))
     :url (str "https://api.adorable.io/avatars/285/" avatar "%40adorable.io")
     :languages [:en :es]
     :name "Expert Man"
     :initials "EM"
     :rating 0.7
     }
  )

(defn conversation
  [expert-name topic start-at]
    {:id (uuid/uuid-string (uuid/make-random-uuid))
     :topic topic
     :start-at start-at
     :expert (expert expert-name)
    }
  )

(defn load-test-data!
 []
  (session/put! :user {
    :profile {
      :name "Cary FitzHugh"
      :avatar (str "https://api.adorable.io/avatars/285/CaryFitzHugh%40adorable.io")
      :email "cary@thegotoexpert.com"
    }
    :conversations {
      :active [ (conversation "active" "Medical Device Repair" (tc/now))]
      :upcoming [
         (conversation "upcoming1" "A Future Medical Device Repair" (tc/plus (tc/now) (tc/days 10)))
         (conversation "upcoming3" "Future Medical Device Repair" (tc/plus (tc/now) (tc/days 8)))
      ]
      :history [
         (conversation "past1" "A Past Medical Device Repair" (tc/minus (tc/now) (tc/days 10)))
         (conversation "past2" "Past Medical Device Repair" (tc/minus (tc/now) (tc/days 8)))
         (conversation "past3" "Past Medical Device Repair" (tc/minus (tc/now) (tc/days 8)))
      ]
    }
  })
  (.log js/console "Injecting:" (pr-str (session/get :user)))
  )
