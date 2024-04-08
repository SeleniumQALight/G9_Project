package signOutTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void validSignOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred().checkIsRedirectToHomePage();
        Assert.assertTrue("Button Search is not displayed",
                pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchDisplayed());
        Assert.assertTrue("Button Chat is not displayed",
                pageProvider.getHomePage().getHeaderElement().checkIsButtonChatDisplayed());
        Assert.assertTrue("Avatar is not displayed",
                pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed());
        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDisplayed());
        Assert.assertFalse("Button Sign In is displayed",
                pageProvider.getLoginPage().checkIsButtonSignInDisplayed());
        Assert.assertFalse("Input UserName is displayed",
                pageProvider.getLoginPage().checkIsInputUserNameDisplayed());
        Assert.assertFalse("Input Password is displayed",
                pageProvider.getLoginPage().checkIsInputPasswordDisplayed());



            pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut().checkIsRedirectToLoginPage();
            Assert.assertTrue("Input UserName is not displayed",
                    pageProvider.getLoginPage().checkIsInputUserNameDisplayed());
            Assert.assertTrue("Input Password is not displayed",
                    pageProvider.getLoginPage().checkIsInputPasswordDisplayed());
            Assert.assertFalse("Button Sign Out is displayed",
                    pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed());
            Assert.assertFalse("Button Create Post is displayed",
                    pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDisplayed());
            Assert.assertFalse("Button Search is displayed",
                    pageProvider.getHomePage().getHeaderElement().checkIsButtonSearchDisplayed());
            Assert.assertFalse("Button Chat is displayed",
                    pageProvider.getHomePage().getHeaderElement().checkIsButtonChatDisplayed());
            Assert.assertFalse("Avatar is displayed",
                    pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed());

        }
}
