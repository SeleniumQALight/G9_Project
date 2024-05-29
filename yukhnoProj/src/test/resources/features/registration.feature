
  Feature: Registration Feature

     @R003
     Scenario Outline: R003 Validation Messages
       Given I open Login page
       When I enter '<username>' into input Username in Registration form
       And I enter '<email>' into input Email in Registration form
       And I enter '<password>' into input Password in Registration form
       And I click on button SignUp in Registration form
       Then I see the following '<errorMessage>'




       Examples:
         | username | email            | password       | errorMessage                                                                                                             |
         | Test1234 | Test1234@test.ts | not_valid      | Password must be at least 12 characters.                                                                                 |
         | Test1234 | not_valid        | valid_password | You must provide a valid email address.                                                                                  |
         | no       | Test1234@test.ts | valid_password | Username must be at least 3 characters.                                                                                  |
         | no       | Test1234@test.ts | not_valid      | Password must be at least 12 characters.;Username must be at least 3 characters.                                         |
         | Test1234 | not_valid        | not_valid      | You must provide a valid email address.;Password must be at least 12 characters.                                         |
         | no       | not_valid        | not_valid      | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
