package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.TestData;
import io.qameta.allure.*;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    //LoginTestWithPageObject#validLogin
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
        //pageProvider.getLoginPage().clickOnButtonSignIn();

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

@Test
    public void validLoginWithExcel() throws IOException {
    Map<String, String> dataForValidLogin =
            ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed"
                , pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }
}