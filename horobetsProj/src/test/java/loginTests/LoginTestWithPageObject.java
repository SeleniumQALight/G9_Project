package loginTests;

import baseTest.BaseTest;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out ia not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());

        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDispayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsUserNameDisplayed();
        pageProvider.getLoginPage().checkIsInputLoginNotDispayed();
        pageProvider.getLoginPage().checkIsInputPasswordNotDispayed();

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out ia not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qqaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In is not visible"
                , pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertFalse("Button Sign Out is visible"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Pop Up is not displayed"
                , pageProvider.getLoginPage().isPopUpDisplayed());


    }

}
