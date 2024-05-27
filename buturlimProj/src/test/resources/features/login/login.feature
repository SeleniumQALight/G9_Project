
Feature: Login Feature
@R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on Home page

  @R002
  Scenario Outline: R002 Invalid Login
    Given I open Login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input Password in Login page
    And I click on Sign In button in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login         | password         |
      | qaauto        | notvalidpassword |
      | notvalidlogin | 123456qwerty     |

