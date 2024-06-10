package bdd.stepDefinitions;

import api.PrivatBankApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static data.PrivatBankTestData.*;

public class PrivatBankSteps extends MainSteps{
    private PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();
    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get exchange rate for {string} via API from PrivatBank")
    public void iGetExchangeRateForViaAPIFromPrivatBank(String currencyName) {
        EXCHANGE_RATE_FROM_API = privatBankApiHelper.getBuyAndSaleRateFromApi(currencyName);
    }

    @When("I get exchange rate for {string} via UI from website PrivatBank")
    public void iGetExchangeRateForViaUIFromWebsitePrivatBank(String currencyName) {
        EXCHANGE_RATE_FROM_UI = pageProvider
                .getPrivatBankMainPage().getBuyAndSaleRateFromUI(currencyName);
    }

    @Then("I verify that exchange rate from API and exchange rate from UI are equal")
    public void iVerifyThatExchangeRateFromApiAndExchangeRateFromUIAreEqual() {
        Assert.assertEquals("Sale rates do not match", Double.parseDouble(EXCHANGE_RATE_FROM_API.get("sale")),
                Double.parseDouble(EXCHANGE_RATE_FROM_UI.get("sale")), 0.01);
        Assert.assertEquals("Buy rates do not match", Double.parseDouble(EXCHANGE_RATE_FROM_API.get("buy")),
                Double.parseDouble(EXCHANGE_RATE_FROM_UI.get("buy")), 0.01);
    }
}