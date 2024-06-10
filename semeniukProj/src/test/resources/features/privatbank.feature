
Feature: PrivatBank feature

  @Exam
  Scenario Outline: Exam Check currency rate
    Given I get exchange rate for '<currencyName>' via API from PrivatBank
    When I get exchange rate for '<currencyName>' via UI from website PrivatBank
    Then I verify that exchange rate from API and exchange rate from UI are equal

    Examples:
      | currencyName |
      | USD          |
      | EUR          |