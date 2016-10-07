(ns clj.expert.styles
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]])
  )

(defstylesheet main
  [:body
    {:font-family "courier"}
    ])
