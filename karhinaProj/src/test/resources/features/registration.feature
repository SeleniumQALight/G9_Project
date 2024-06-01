Feature: Registration Feature

@R003
Scenario Outline: R003 Registration with invalid cred
Given I open Login page
When I enter '<username>' into input Username in Registration form on Login page
And I enter '<email>' into input Email in Registration form on Login page
And I enter '<password>' into input Password in Registration form on Login page
And I click on button SignUpForOurApp in Registration form on Login page
Then I see registration alert messages '<error registration massage>'


Examples:

  | username                        | email                  | password                                             | error registration massage                                                                                               |
  | u                               | karginaolena@gmail.com | valid_password                                       | Username must be at least 3 characters.                                                                                  |
  | karginaolena                    | not_valid_email        | valid_password                                       | You must provide a valid email address.                                                                                  |
  | karginaolena                    | karginaolena@gmail.com | not_valid                                            | Password must be at least 12 characters.                                                                                 |
  | karginaolena                    | karginaolena@gmail.com | not_valid_password_not_valid_password_not_valid_pass | Password cannot exceed 50 characters.                                                                                    |
  | notvalidusernamenotvalidusernam | karginaolena@gmail.com | valid_password                                       | Username cannot exceed 30 characters.                                                                                    |
  | ka                              | not_valid_email        | not_valid                                            | You must provide a valid email address.;Username must be at least 3 characters.;Password must be at least 12 characters. |

