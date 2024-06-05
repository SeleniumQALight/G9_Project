Feature: Registration Feature

  @R003
  Scenario Outline: R003 Validation Messages Test
    Given I open login page
    When I enter '<username>' into input Registration UserName field in Login page
    And I enter '<email>' into input Registration Email field in Login page
    And I enter '<password>' into input Registration Password field in Login page
    Then I see the following '<error_messages>' in the Registration fields


    Examples:
      | username     | email           | password     | error_messages                                                                   |
      | autotetiana1 | not_valid_email | unvalid_pas  | You must provide a valid email address.;Password must be at least 12 characters. |
      | autotetiana2 | not_valid_email | Qwerty123456 | You must provide a valid email address.                                          |

