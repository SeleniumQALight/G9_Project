Feature: Registration Feature

  @R003
  Scenario Outline: R003 Check validation messages on registration area
    Given I open Login page
    When I enter '<username>' into input userName on Login page at Registration area
    And I enter '<email>' into input Email on Login page at Registration area
    And I enter '<password>' into input Password on Login page at Registration area
    And I click on button Sign Up on Login page at Registration area
    Then I see message with text '<validation_text>'

    Examples:
      | username | email                 | password     | validation_text                                                                                                            |
      | tr       | cogevom210@adrais.com | testtesttest | Username must be at least 3 characters.                                                                                    |
      | test     | notValid              | testtesttest | You must provide a valid email address.                                                                                    |
      | test     | cogevom210@adrais.com | notValid     | Password must be at least 12 characters.                                                                                   |
      | test     | notValid              | notValid     | You must provide a valid email address.; Password must be at least 12 characters.                                          |
      | tr       | notValid              | notValid     | Username must be at least 3 characters.; You must provide a valid email address.; Password must be at least 12 characters. |
      | tr       | cogevom210@adrais.com | notValid     | Username must be at least 3 characters.; Password must be at least 12 characters.                                          |