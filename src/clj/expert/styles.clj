(ns expert.styles
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]])
  )

(defstyles main
  [:section {
    :padding "80px 0"
    }
  ]
  [:.landing-header
    {
      :background "url(http://placehold.it/2600x2200?text=+) no-repeat center center"
      ;;:background "url(/images/landing-bg.jpg) no-repeat center center"
      :background-size :cover
      :text-align :center
      :padding "50px 0"
      :min-height "500px"
    }
   ]
  )
