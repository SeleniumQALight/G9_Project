Feature: Login Feature

  @R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Login with invalid cred
    Given I open Login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input Password in Login page
    And I click on button SignIn In Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password       |
      | qaauto          | not_valid_pass |
      | not_valid_login | 123456qwerty   |