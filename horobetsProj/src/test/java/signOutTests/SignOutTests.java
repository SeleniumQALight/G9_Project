package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTests extends BaseTest {


    @Test
    public void TC_001_validSignOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .chekIsRedirectToHomePage()
        ;
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDispayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getLoginPage().checkIsInputLoginNotDispayed();
        pageProvider.getLoginPage().checkIsInputPasswordNotDispayed();
        pageProvider.getLoginPage().checkIsButtonSignInNotDisplayed();


        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonChatNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotDisplayed();
        pageProvider.getLoginPage().checkIsInputLoginDispayed();
        pageProvider.getLoginPage().checkIsInputPasswordDispayed();
        pageProvider.getLoginPage().checkIsButtonSignInDisplayed();

    }
}

