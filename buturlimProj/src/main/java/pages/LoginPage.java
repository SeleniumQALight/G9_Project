package pages;


import data.TestData;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pages.elements.HeaderElement;
import pages.libs.DB_Util_seleniumUsers;
import pages.libs.Util;

public class LoginPage extends ParentPage {


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @FindBy(xpath = ".//button[contains(text(), 'Sign In')]") // ініціалізується в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = ".//div[contains(text(), 'Invalid username/password.')]")
    private WebElement loginError;


    @FindBy(id = "username-register") //    xpath = ".//input[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;

    final String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Log In Page was opened with URL " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    @Step
    public void enterTextIntoInputPassword(String text) {
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginError, "Login Error");
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn, "Button Sign In");
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCredNew() throws SQLException, ClassNotFoundException {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI_NEW);
        DB_Util_seleniumUsers db_util_seleniumUsers = new DB_Util_seleniumUsers();
        String password = db_util_seleniumUsers.getPassForLogin(TestData.VALID_LOGIN_UI_NEW);
        enterTextIntoInputPassword(password);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkIsRedirectOnLoginPage() {
        checkUrl();
        return this;
    }

    @Step
    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputUserNameLoginForm, "Input Login");
        return this;
    }

    @Step
    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPasswordLoginForm, "Input Password");
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    @Step
    public LoginPage checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputUserNameLoginForm, "Input Login");
        return this;
    }

    @Step
    public LoginPage checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPasswordLoginForm, "Input Password");
        return this;
    }


    @Step
    public LoginPage enterTextIntoRegistrationUsernameField(String username) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, username);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        cleanAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        cleanAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessagesTexts(String messages) {
        // error1;error2; error3 -> [error1, error2, error3]
        String[] errors = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), errors.length));
        Util.waitABit(1);

        Assert.assertEquals("Number of messages", errors.length, listErrorsMessages.size());

        ArrayList<String> actualTextMessages = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualTextMessages.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < errors.length; i++) {
            softAssertions.assertThat(actualTextMessages.get(i))
                    .as("Message " + i)
                    .isIn(errors);
        }
        softAssertions.assertAll();
        return this;
    }
}


