package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MyProfilePageSteps extends MainSteps{
    public MyProfilePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} posts in Post list on MyProfile page")
    public void iSeePostsInPostListOnMyProfilePage(int numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(numberOfPosts);
    }
}
