package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void validSignOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred().checkIsRedirectToHomePage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDisplayed();
        pageProvider.getLoginPage().checkIsButtonSignInIsNotDisplayed();
        pageProvider.getLoginPage().checkIsInputUserNameIsNotDisplayed();
        pageProvider.getLoginPage().checkIsInputPasswordIsNotDisplayed();

        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        pageProvider.getLoginPage().checkIsInputUserNameDisplayed();
        pageProvider.getLoginPage().checkIsInputPasswordDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostIsNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchIsNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatIsNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileIsNotDisplayed();

        }
}
