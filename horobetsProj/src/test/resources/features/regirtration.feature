Feature: Registration Feature

  @R003
  Scenario Outline: R003 Validation messages test
    Given  I open Login page
    When I enter '<username>' into input Username in Registration form on Login page
    And  I enter '<email>' into input Email in Registration form on Login page
    And  I enter '<password>' into input Password in Registration form on Login page
    And I click on button SignUp in Registration form on Login page
    Then I see error messages '<error_message>'

    Examples:
      | username | email               | password       | error_message                                                                                                            |
      | i        | horobetsira@ukr.net | valid_password | Username must be at least 3 characters.                                                                                  |
      | iraH     | not_valid           | valid_password | You must provide a valid email address.                                                                                  |
      | iraH     | horobetsira@ukr.net | not_valid      | Password must be at least 12 characters.                                                                                 |
      | i        | not_valid           | not_valid      | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |