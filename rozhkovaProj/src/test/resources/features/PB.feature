Feature: PB Feature
  @PB001
  Scenario Outline: Check Exchange Currency Rate
    Given I get the exchange rate for <currency> by PrivatBank API
    When I get the exchange rate for <currency> by PrivatBank UI
    Then I check that exchange rate for <currency> by PrivatBank API and PrivatBank UI are equal

    Examples:
      | currency |
      | USD      |
      | EUR      |