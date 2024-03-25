package loginTests;

import baseTests.BaseTests;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTests {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().isButtonSignOutDisplayed());
    }

    @Test
    public void NotValidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto22");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is not displayed", pageProvider.getHomePage().isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In is not visible", pageProvider.getHomePage().isButtonSignInDisplayed());
        Assert.assertTrue("Alert message is not visible", pageProvider.getHomePage().isAlertMessageDisplayed());
    }
}
