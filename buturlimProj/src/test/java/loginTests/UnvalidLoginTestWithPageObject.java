package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class UnvalidLoginTestWithPageObject extends BaseTest {

    @Test
    public void unvalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("notvalidname");
        pageProvider.getLoginPage().enterTextIntoInputPassword("notvalidpass");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Error is not displayed", pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertTrue("Error is not displayed", pageProvider.getLoginPage().isLoginErrorDisplayed());
        Assert.assertFalse("Button Sign Out is not displayed", pageProvider.getHomePage().isButtonSignOutDisplayed());
    }

}
