package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.Then;

public class HomePageSteps extends MainSteps{
    public HomePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on Home page")
    public void i_see_avatar_on_home_page() {
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfilePresent();
    }
}
