package pages;

import data.TestData;
import io.qameta.allure.Step;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //Ініціалізується в Common ActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
        private  WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement massageInvalidUsernamePassword;

    @FindBy(xpath = "//*[@id='username-register']") //id="username-register"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(xpath = "//*[@id='email-register']") //id="email-register"
    private WebElement inputEmailRegistrationForm;

    @FindBy(xpath = "//*[@id='password-register']") //id="password-register"
    private WebElement inputPasswordRegistrationForm;

    final static String listErrorsMessagesLocator
            = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listErrorsMessages;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login Page was opened with URL " + baseUrl);
        } catch (Exception e){
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    @Step
    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }
    @Step
    public void  enterTextIntoPassword(String text){
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }
    @Step
    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }
    @Step
    public boolean isButtonSignInDisplayed() {return isElementDisplayed(buttonSignIn);
    }
    @Step
    public boolean isInvalidUsernamePasswordDisplayed() {
        return isElementDisplayed(massageInvalidUsernamePassword);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid Page Not Home Page", isButtonSignInDisplayed());
        return this;
    }

    @Step
    public boolean checkIsInputUserNameDisplayed() {
        return checkElementIsDisplayed(inputUserNameLoginForm);
    }
    @Step
    public boolean checkIsInputPasswordDisplayed() {
        return checkElementIsDisplayed(inputPasswordLoginForm);
    }
    @Step
    public boolean checkIsButtonSignInDisplayed() {
        return checkElementIsDisplayed(buttonSignIn);
    }
    @Step
    public boolean checkIsInputUserNameIsNotDisplayed() {
        return checkElementIsNotDisplayed(inputUserNameLoginForm);
    }
    @Step
    public boolean checkIsInputPasswordIsNotDisplayed() {
        return checkElementIsNotDisplayed(inputPasswordLoginForm);
    }
    @Step
    public boolean checkIsButtonSignInIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonSignIn);
    }
    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
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
    public LoginPage checkErrorsMessages(String messages) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrors = messages.split(";");

        webDriverWait10.until(ExpectedConditions
                .numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);

        Assert.assertEquals("Number of Messages", expectedErrors.length, listErrorsMessages.size());

        ArrayList<String> actualTextMessages = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualTextMessages.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(actualTextMessages.get(i))
                            .as("Error " + i)
                    .isIn(expectedErrors);
        }

        softAssertions.assertAll();

        return this;
    }

}

