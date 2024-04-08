package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button sign out is not displayed",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertFalse("Button Sign In is displayed",
                pageProvider.getHomePage().isButtonSignInDisplayed());

        Assert.assertTrue("Button Create Post is not displayed",
                pageProvider.getHomePage().getHeaderElement().isCreatePostDisplayed());
        Assert.assertTrue("Button My Profile is not displayed",
                pageProvider.getHomePage().getHeaderElement().isMyProfileDisplayed());
        Assert.assertTrue("User Name is not displayed",
                pageProvider.getHomePage().getHeaderElement().isUserNameDisplayed());
        Assert.assertFalse("User Name input is displayed",
                pageProvider.getLoginPage().isUserNameInputDisplayed());
        Assert.assertFalse("Password input is displayed",
                pageProvider.getLoginPage().isPasswordInputDisplayed());


    }
    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto123");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In is not visible ",
                pageProvider.getHomePage().isButtonSignInDisplayed());
        Assert.assertFalse("Button Sing out is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Pop up isn't displayed",
                pageProvider.getHomePage().isPopUpDisplayed());

    }
}
