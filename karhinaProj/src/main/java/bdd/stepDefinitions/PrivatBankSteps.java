package bdd.stepDefinitions;

import apiPrivatBank.ApiHelperPrivatBank;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class PrivatBankSteps extends MainSteps{

    public PrivatBankSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    private ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("I get the exchange rates for {} by send API request to PrivatBank")
    public void iGetTheExchangeRatesForBySendAPIRequestToPrivatBank(String currency) {
        TestData.EXCHANGE_RATE_BY_API = apiHelperPrivatBank.getExchangeRateByApi(currency);
    }

    @When("I get the exchange rates for {} by UI PrivatBank")
    public void iGetTheExchangeRatesForByUIPrivatBank(String currency) {
       TestData.EXCHANGE_RATE_BY_UI = pageProvider.getPrivatBankPage().getBuyAndSaleRateFromUI(currency);
    }


    @Then("I should see the exchange rates for ccy {} and compare them with the rates from the API")
    public void iShouldSeeTheExchangeRatesForCcyAndCompareThemWithTheRatesFromTheAPI(String currency) {


        Assert.assertEquals("Exchange rates for buy do not match", Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("buy")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("buy")), 0.01);
        Assert.assertEquals("Exchange rates for sale do not match", Double.parseDouble(TestData.EXCHANGE_RATE_BY_API.get("sale")),
                Double.parseDouble(TestData.EXCHANGE_RATE_BY_UI.get("sale")), 0.01);
    }

}
