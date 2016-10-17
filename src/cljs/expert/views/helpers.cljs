(ns expert.views.helpers)

(defn input-element
  "An input element which updates its value on change"
  [id name type value & rest]
  [:input (merge {:id id
           :name name
           :class "form-control"
           :type type
           :required ""
           :defaultValue (name @value)
           :on-change (fn [evt] (swap! value assoc name (-> evt .-target .-value)) )}
          (apply hash-map rest))])
