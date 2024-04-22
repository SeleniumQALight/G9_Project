package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.libs.ConfigProvider;
import pages.libs.ExcelDriver;

import java.io.IOException;
import java.util.Map;


public class LoginTestWithPageObject extends BaseTest {
    final String VALID_LOGIN = "qaauto";
    final String VALID_PASSWORD = "123456qwerty";

    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectOnHomePage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsUserNameVisible();

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectOnHomePage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsUserNameVisible();
    }
}
