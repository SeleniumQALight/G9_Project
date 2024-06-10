Feature: Privat Bank feature
  @R005
  Scenario Outline: R005 Exchange scenario
    Given I get the exchange rate for '<ccy>' to UAH by sending API request
    When I get exchange rate for '<ccy>' to UAH by PrivatBank UI
    Then I should see the exchange rates for '<ccy>' and compare them
  Examples:
    | ccy |
    | USD |
    | EUR |

