
Feature: Login Feature
@R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on Home page