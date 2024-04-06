package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void validSignOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred().checkIsRedirectToHomePage();
        Assert.assertTrue("Button Search is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSearchDisplayed());
        Assert.assertTrue("Button Chat is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonChatDisplayed());
        Assert.assertTrue("Avatar is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonMyProfileDisplayed());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonCreatePostDisplayed());
        Assert.assertTrue("Button Sign In is not displayed",
                pageProvider.getHomePage().isButtonSignInHomePageIsNotDisplayed());
        Assert.assertTrue("Input UserName is displayed",
                pageProvider.getHomePage().isInputUserNameHomePageIsNotDisplayed());
        Assert.assertTrue("Input Password is displayed",
                pageProvider.getHomePage().isInputPasswordHomePageIsNotDisplayed());



            pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
            Assert.assertTrue("Input UserName is not displayed",
                    pageProvider.getLoginPage().isInputUserNameDisplayed());
            Assert.assertTrue("Input Password is not displayed",
                    pageProvider.getLoginPage().isInputPasswordDisplayed());
            Assert.assertTrue("Button Sign Out Sign out is displayed",
                    pageProvider.getHomePage().getHeaderElement().isButtonSignOutNotDisplayed());
            Assert.assertTrue("Button Create Post is displayed",
                    pageProvider.getHomePage().getHeaderElement().isButtonCreatePostNotDisplayed());
            Assert.assertTrue("Button Search is displayed",
                    pageProvider.getHomePage().getHeaderElement().isButtonSearchNotDisplayed());
            Assert.assertTrue("Button Chat is displayed",
                    pageProvider.getHomePage().getHeaderElement().isButtonChatNotDisplayed());
            Assert.assertTrue("Avatar is displayed",
                    pageProvider.getHomePage().getHeaderElement().isButtonMyProfileNotDisplayed());
        }
}
