
  Feature: Privatbank feature

     @Exam
    Scenario Outline: Exam Check currency rate
     Given I get exchange rate for '<currencyName>' via API from Privatbank
     When I get exchange rate for '<currencyName>' via UI from Privatbank website
     Then I verify that exchange rate from Api and exchange rate from UI are equal




      Examples:
        | currencyName |
        | USD          |
        | EUR          |