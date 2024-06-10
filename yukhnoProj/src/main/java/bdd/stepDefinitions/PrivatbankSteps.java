package bdd.stepDefinitions;

import api.PrivatbankApiHelper;
import bdd.helpers.WebDriverHelper;
import data.PrivatbankTestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static data.PrivatbankTestData.*;

public class PrivatbankSteps extends MainSteps{
    private PrivatbankApiHelper privatbankApiHelper = new PrivatbankApiHelper();
    public PrivatbankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get exchange rate for {string} via API from Privatbank")
    public void iGetExchangeRateForViaAPIFromPrivatbank(String currencyName) {
        EXCHANGE_RATE_FROM_API = privatbankApiHelper.getBuyAndSaleRateFromApi(currencyName);
    }

    @When("I get exchange rate for {string} via UI from Privatbank website")
    public void iGetExchangeRateForViaUIFromPrivatbankWebsite(String currencyName) {
        EXCHANGE_RATE_FROM_UI = pageProvider
                .getPrivatbankMainPage().getBuyAndSaleRateFromUI(currencyName);
    }

    @Then("I verify that exchange rate from Api and exchange rate from UI are equal")
    public void iVerifyThatExchangeRateFromApiAndExchangeRateFromUIAreEqual() {
        Assert.assertEquals("Sale rates do not match", Double.parseDouble(EXCHANGE_RATE_FROM_API.get("sale")),
                Double.parseDouble(EXCHANGE_RATE_FROM_UI.get("sale")), 0.01);
        Assert.assertEquals("Buy rates do not match", Double.parseDouble(EXCHANGE_RATE_FROM_API.get("buy")),
                Double.parseDouble(EXCHANGE_RATE_FROM_UI.get("buy")), 0.01);
    }
}
