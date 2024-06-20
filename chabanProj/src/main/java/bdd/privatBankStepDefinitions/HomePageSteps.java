package bdd.privatBankStepDefinitions;

import bdd.helpers.WebDriverHelper;
import bdd.stepDefinitions.MainSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import privatBankApi.ApiHelperPrivatBank;

public class HomePageSteps extends MainSteps {
    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    ApiHelperPrivatBank apiHelperPrivatBank = new ApiHelperPrivatBank();

    @Given("I perform an API Call")
    public void iPerformAnAPICall() {
        apiHelperPrivatBank.getExchangeRatesAPI();
    }


    @When("I open the PrivatBank website")
    public void iOpenThePrivatBankWebsite() {
        pageProvider.getPrivatBankHomePage().openHomePage().scrollToCourseContainer();
    }

    @Then("I check the {string} rate for {string}")
    public void iCheckTheBuyRateFor(String currency, String rate) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat( apiHelperPrivatBank.mapAndGetRate(currency,rate))
                .isEqualTo(pageProvider.getPrivatBankHomePage().getCurrency(rate, currency));
        softAssertions.assertAll();



    }


}