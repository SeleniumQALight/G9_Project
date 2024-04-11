package pages;


import data.TestData;
import io.opentelemetry.api.internal.Utils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import pages.elements.HeaderElement;
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

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Log In Page was opened with URL " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoInputPassword(String text) {
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginError, "Login Error");
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn, "Button Sign In");
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectOnLoginPage() {
        checkUrl();
        return this;
    }

    public LoginPage checkIsInputLoginVisible() {
        checkIsElementVisible(inputUserNameLoginForm, "Input Login");
        return this;
    }

    public LoginPage checkIsInputPasswordVisible() {
        checkIsElementVisible(inputPasswordLoginForm, "Input Password");
        return this;
    }

    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage checkIsButtonSignInNotVisible() {
        checkIsElementNotVisible(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage checkIsInputLoginNotVisible() {
        checkIsElementNotVisible(inputUserNameLoginForm, "Input Login");
        return this;
    }

    public LoginPage checkIsInputPasswordNotVisible() {
        checkIsElementNotVisible(inputPasswordLoginForm, "Input Password");
        return this;
    }


    public LoginPage enterTextIntoRegistrationUsernameField(String username) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, username);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        cleanAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        cleanAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

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


