package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import privatBankApi.PrivatApiHelper;

public class PrivatSteps extends MainSteps {
    private final PrivatApiHelper privatApiHelper;

    public PrivatSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        this.privatApiHelper = new PrivatApiHelper();
    }

    @Given("I get the exchange rate for {string} to UAH by sending API request")
    public void iGetTheExchangeRateForToUAHBySendingAPIRequest(String currency) {
        TestData.EXCHANGE_RATE_API = privatApiHelper.getExchangeRatesFromApi(currency);
    }

    @When("I get exchange rate for {string} to UAH by PrivatBank UI")
    public void iGetExchangeRateForToUAHByPrivatBankUI(String currency) {
        TestData.EXCHANGE_RATE_UI = pageProvider.getPrivatBankPage().getExchangeRatesFromUI(currency);
    }

    @Then("I should see the exchange rates for {string} and compare them")
    public void iShouldSeeTheExchangeRatesForAndCompareThem(String currency) {
        Assert.assertEquals("BUY exchange rates don't match", Double.parseDouble(TestData.EXCHANGE_RATE_API.get("buy")),
                Double.parseDouble(TestData.EXCHANGE_RATE_UI.get("buy")), 0.01);
        Assert.assertEquals("SELL exchange rates don't match", Double.parseDouble(TestData.EXCHANGE_RATE_API.get("sale")),
                Double.parseDouble(TestData.EXCHANGE_RATE_UI.get("sale")), 0.01);
    }
}
