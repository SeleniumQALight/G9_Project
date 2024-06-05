Feature: Exchange Rates Feature

  @R005
  Scenario Outline: @R005 - Receiving and comparing exchange rates
   Given I get the exchange rates for <ccy> by send API request to PrivatBank
   When I get the exchange rates for <ccy> by UI PrivatBank
   Then I should see the exchange rates for ccy <ccy> and compare them with the rates from the API

    Examples:

      | ccy |
      | USD |
      | EUR |






