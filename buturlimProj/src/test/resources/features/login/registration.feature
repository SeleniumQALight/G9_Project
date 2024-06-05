Feature: Registration Feature
  @R003
  Scenario Outline: R003 Validation Messages
    Given I open Login page
    When I enter '<username>' into Username registration input
    And I enter '<email>' into Email registration input
    And I enter '<password>' into Password registration input
    And I click on Sign Up button
    Then I see '<message>' message

  Examples:
    | username                         | email              | password                                            | message                                                                                                                  |
    | mariiab111                       | tt                 | tt                                                  | You must provide a valid email address.;Password must be at least 12 characters.                                         |
    | mariiab111                       | mariiab1@email.com | tt                                                  | Password must be at least 12 characters.                                                                                 |
    | tt                               | tt                 | tt                                                  | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
    | tttttttttttttttttttttttttttttttt | tt                 | ttttttttttttttttttttttttttttttttttttttttttttttttttt | Username cannot exceed 30 characters.;You must provide a valid email address.;Password cannot exceed 50 characters.      |
    | tttttttttttttttttttttttttttttt   | tt                 | tttttt111111                                        | You must provide a valid email address.                                                                                  |
    | tttttttttttttttttttttttttttttt   | tt                 | ttttttttttttttttttttttttttttttttttttttttttttttttttt | You must provide a valid email address.;Password cannot exceed 50 characters.                                            |