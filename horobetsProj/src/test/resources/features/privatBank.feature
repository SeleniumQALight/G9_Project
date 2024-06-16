Feature: Privat Bank feature

  @R005
  Scenario Outline: Exam - receiving and comparing exchange rates

    Given I get the exchange rates for <ccy> to UAH by API request to PrivatBank
    When I get the exchange rates for <ccy> to UAH by UI PrivatBank
    Then I should see the exchange rates for <ccy> and compare them

    Examples:
      | ccy |
      | USD |
      | EUR |