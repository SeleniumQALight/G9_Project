package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTests extends BaseTest {


    @Test
    public void TC_001_validSignOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .chekIsRedirectToHomePage();

        Assert.assertTrue("Button Search is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSearchDisplayed());
        Assert.assertTrue("Button Chat is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonChatDisplayed());
        Assert.assertTrue("Button My Profile is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonMyProfileDisplayed());
        Assert.assertTrue("Button Create Post is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonCreatePostDisplayed());
        Assert.assertTrue("Button Sign Out is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Input Login is displayed"
                , pageProvider.getLoginPage().isInputLoginNotDisplayed());
        Assert.assertTrue("Input Password is displayed"
                , pageProvider.getLoginPage().isInputPasswordNotDisplayed());
        Assert.assertTrue("Button Sign In is displayed"
        , pageProvider.getLoginPage().isButtonSignInNotDisplayed());

        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
        Assert.assertTrue("Button Search is displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSearchNotDisplayed());
        Assert.assertTrue("Button Chat is displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonChatNotDisplayed());
        Assert.assertTrue("Button My Profile is displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonMyProfileNotDisplayed());
        Assert.assertTrue("Button Create Post is displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonCreatePostNotDisplayed());
        Assert.assertTrue("Button Sign Out is displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutNotDisplayed());

        Assert.assertTrue("Input Login is not displayed"
                , pageProvider.getLoginPage().isInputLoginDisplayed());
        Assert.assertTrue("Input Password is not displayed"
                , pageProvider.getLoginPage().isInputPasswordDisplayed());
        Assert.assertTrue("Button Sign In is not displayed"
                , pageProvider.getLoginPage().isButtonSignInDisplayed());



    }
}

