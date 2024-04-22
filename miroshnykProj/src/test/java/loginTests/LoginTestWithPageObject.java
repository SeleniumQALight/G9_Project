package loginTests;

import Categories.SmokeTestFilter;
import baseTest.BaseTest;
import jdk.jfr.Category;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }
}
