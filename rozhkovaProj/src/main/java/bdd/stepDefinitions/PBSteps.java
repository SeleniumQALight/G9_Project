package bdd.stepDefinitions;


import apiPB.helpers.ApiHelperPB;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class PBSteps extends MainSteps{

    private final ApiHelperPB apiHelperPB = new ApiHelperPB();

    public PBSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I get the exchange rate for {} by PrivatBank API")
    public void iGetTheExchangeRateForByPrivatBankApi(String currency) {
        TestData.EXCHANGE_RATE_BY_API = apiHelperPB.getExchangeRateByAPI(currency);
    }

    @When("I get the exchange rate for {} by PrivatBank UI")
    public void iGetTheExchangeRateForByPrivatBankUI(String currency) {
        TestData.EXCHANGE_RATE_BY_UI = pageProvider.getPBPage().getCurrencyRatesFromUI(currency);
    }

    @Then("I check that exchange rate for {} by PrivatBank API and PrivatBank UI are equal")
    public void iCheckThatExchangeRateByPrivatBankAPIAndPrivatBankUIAreEqual(String currency) {
        Assert.assertEquals("Exchange rate by PrivatBank API and PrivatBank UI are not equal",
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("buy")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("buy")),
                0.01);

        Assert.assertEquals("Exchange rate by PrivatBank API and PrivatBank UI are not equal",
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("sell")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("sell")),
                0.01);

        Logger logger = Logger.getLogger(getClass());
        logger.info("Exchange rate from UI for " + currency + " is: " + TestData.EXCHANGE_RATE_BY_UI + "\n" +
                " and Exchange rate from API is: " + TestData.EXCHANGE_RATE_BY_API + ". " + "So, they are equal.");
    }
}
