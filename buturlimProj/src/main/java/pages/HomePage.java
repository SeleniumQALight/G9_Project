package pages;


import data.TestData;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;
import pages.libs.DB_Util_seleniumUsers;

import java.sql.SQLException;

public class HomePage extends ParentPage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
    @Step
    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }
    @Step
    public HomePage checkIsRedirectOnHomePage() {
        checkUrl();
        return this;
    }

    @Step
    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

    @Step
    public HomePage openHomePageAndLoginIfNeededWithDb() throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI_NEW);
            DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();
            String password = db_util_seleniumUsers.getPassForLogin(TestData.VALID_LOGIN_UI_NEW);
            loginPage.enterTextIntoInputPassword(password);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

}
