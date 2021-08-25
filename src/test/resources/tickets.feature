Feature: Tickets reservation service check

  Scenario: checking reservation data via API
    Given flight from "RIX" to "SVO"
    And passenger info:
      | name             | random     |
      | surname          | Brikina    |
      | discount         | CCCCCC     |
      | passengers count | 4          |
      | child count      | 2          |
      | luggage          | 2          |
      | date             | 16-05-2018 |
    And seat number is: 21

    When we are opening home page
    And selecting airports

    Then airports are displayed on passenger info page

    When we are submitting passenger info

    Then name appears in summary
    And price calculated is: 3600

    When we are passing Book button
    And selecting seat number

    Then seat number appears on page

    When we are booking flight

    Then success message appears

    When we are requesting reservations via API

    Then our reservation with correct data appears