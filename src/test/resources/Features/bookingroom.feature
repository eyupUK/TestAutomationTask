Feature: Booking a deluxe room


  Scenario: User is able to book a most expensive room under Deluxe Apartment
    Given go to home page
    And select any valid date
    And select "4" nights
    When click "Book now !" button
    And find "Deluxe Appartment"
    And select the most expensive package
    And select any 2 add ons
    When click "Add the selected services >>" button
    Then verify all details
    Given enter contact info
    And select payment method "Credit Card"
    When tick agreement policy
    And click "Create Booking" button
    Given enter credit card info
    And enter address info
    When click Pay button
    Then verify booking complete message "Thank you for your booking!"