package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import io.qameta.allure.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.libs.ConfigProvider;
import pages.libs.ExcelDriver;

import java.io.IOException;
import java.util.Map;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    final String VALID_LOGIN = "qaauto";
    final String VALID_PASSWORD = "123456qwerty";

    @Test
    @Category(SmokeTestFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
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
