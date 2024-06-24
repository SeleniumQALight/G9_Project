package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PrivatBankSteps extends MainSteps {

    private ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the exchange rates for {} to UAH by API request to PrivatBank")
    public void iGetTheExchangeRatesForBySendAPIRequestToPrivatBank(String currency) {
        TestData.EXCHANGE_RATE_BY_API = apiHelperPrivatBank.getExchangeRateByApi(currency);
    }

    @When("I get the exchange rates for {} to UAH by UI PrivatBank")
    public void iGetTheExchangeRatesForByUIPrivatBank(String currency) {
        TestData.EXCHANGE_RATE_BY_UI = pageProvider.getPrivatBankPage().getBuyAndSaleRateFromUI(currency);
    }

    @Then("I should see the exchange rates for {} and compare them")
    public void iShouldSeeTheExchangeRatesForAndCompareThem(String currency) {

        Assert.assertEquals("Exchange rates for buy do not match",
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("buy")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("buy")), 0.01);

        Assert.assertEquals("Exchange rates for sale do not match",
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("sale")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("sale")), 0.01);

    }
}
