package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class LoginTestWithPageObject extends BaseTest {
    final String VALID_LOGIN = "qaauto";
    final String VALID_PASSWORD = "123456qwerty";

    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button My profile is not displayed", pageProvider.getHomePage().getHeaderElement().isMyProfileButtonDisplayed());
        Assert.assertTrue("Button Create Post is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonCreatePostDisplayed());
        Assert.assertEquals("Profile name is not displayed", VALID_LOGIN, pageProvider.getHomePage().getHeaderElement().getProfileNameText());
        Assert.assertFalse("Button Sign In is displayed", pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertFalse("Input Login is displayed", pageProvider.getLoginPage().isInputLoginDisplayed());
        Assert.assertFalse("Input Password is displayed", pageProvider.getLoginPage().isInputPasswordDisplayed());
    }
}
