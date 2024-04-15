package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("VALID_LOGIN_UI");
        pageProvider.getLoginPage().enterTextIntoInputPassword("VALID_PASSWORD_UI");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
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
        pageProvider.getLoginPage().enterTextIntoInputLogin("NotValidLogin");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In is not visible",
                pageProvider.getHomePage().isButtonSignInDisplayed());
        Assert.assertFalse("Button Sing Out is visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Popup isn't displayed",
                pageProvider.getHomePage().isPopUpDisplayed());
    }
    }