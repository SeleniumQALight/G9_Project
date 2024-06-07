Feature: Privat Bank Exchange Rate Feature

  @R001
  Scenario Outline: R001 Check Exchange Rates

    Given I perform an API Call
    When  I open the PrivatBank website
    Then  I check the 'buy' rate for "<currency>"
    And I check the 'sell' rate for "<currency>"


    Examples:
      | currency |
      | USD      |
      | EUR      |
