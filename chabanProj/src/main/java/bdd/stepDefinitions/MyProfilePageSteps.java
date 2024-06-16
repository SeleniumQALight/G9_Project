package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MyProfilePageSteps extends MainSteps{
    public MyProfilePageSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();

    }

    @And("I see {} posts in Posts list on MyProfile page")
    public void iSeeNumberOfPostsPostsInPostsListOnMyProfilePage(int numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPostsInPosts(numberOfPosts);
    }


}
