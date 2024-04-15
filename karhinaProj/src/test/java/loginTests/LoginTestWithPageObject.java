package loginTests;

import baseTest.BaseTest;
import data.TestData;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    final String USER_NAME = "qaauto";
    @Test
    public  void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonMyProfileDisplayed();
        pageProvider.getHomePage().getHeaderElement().checkIsUsernameIsPresent(USER_NAME);
        pageProvider.getLoginPage().checkIsInputUserNameIsNotDisplayed();
        pageProvider.getLoginPage().checkIsInputPasswordIsNotDisplayed();

    }

    @Test
    public void invalidLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto123");
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();






        Assert.assertTrue("Button Sign In  in is not visible", pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertFalse("Button Sign Out Sign out is not visible", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Massage 'Invalid username/password' is not visible", pageProvider.getLoginPage().isInvalidUsernamePasswordDisplayed());
    }

    @Test
    public void testLoginInNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().closeNewTabAndSwitchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();


    }

    @Test
    public void testLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign In  in is not visible",
                pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());


    }
}
