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
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //ініціалізується в commonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(id="username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id="email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id="password-register")
    private WebElement inputPasswordRegistrationForm;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement buttonSignUp;

    final String listErrorsMessagesLocator= "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listErrorsMessagesLocator)
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
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened with URL " + baseUrl);

        }catch (Exception e){
            logger.error("Can not open login page " + e);
            Assert.fail("Can not open login page " + e);
        }
    }
    @Step
    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);

    }
    @Step
    public void enterTextIntoInputPassword(String text) {
//        WebElement inputPasswordLoginForm =
//                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//        inputPasswordLoginForm.clear();
//        inputPasswordLoginForm.sendKeys(text);
//        logger.info(text + " password was inserted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);

    }
    @Step
    public void clickOnButtonSignIn(){
//        WebElement buttonSignIn =
//                webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
//        buttonSignIn.click();
//        logger.info("button was clicked");
        clickOnElement(buttonSignIn);
    }
    @Step
    public void clickOnButtonSignUp(){
        clickOnElement(buttonSignUp);
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
    public boolean isUserNameInputDisplayed(){
        return isElementDisplayed(inputUserNameLoginForm);
    }
    @Step
    public boolean isPasswordInputDisplayed(){
        return isElementDisplayed(inputPasswordLoginForm);
    }
    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrlWithPattern();
        //TODO check some element that is only on this page //dz
        return this;
    }
    @Step
    public LoginPage assertionsForLoginPageElementsDisplayed() {
        checkElementIsDisplayed(inputUserNameLoginForm);
        checkElementIsDisplayed(inputPasswordLoginForm);
        checkElementIsDisplayed(buttonSignIn);
        return this;
    }
    @Step
    public LoginPage assertionsForLoginPageElementsAreNotDisplayed() {
        checkElementIsNotDisplayed(inputUserNameLoginForm);
        checkElementIsNotDisplayed(inputPasswordLoginForm);
        checkElementIsNotDisplayed(buttonSignIn);
        return this;
    }
    @Step
    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
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
    public LoginPage checkErrorsMessagesText(String messages) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrors = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);

        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

        ArrayList<String>actualTextMessages = new ArrayList<>();
        for(WebElement element : listErrorsMessages){
            actualTextMessages.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertAll();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(actualTextMessages.get(i))
                    .as("Error " + i)
                    .isIn(expectedErrors);
        }
        return this;
    }
    @Step
    public LoginPage refreshPage() {
        webDriver.navigate().refresh();
        webDriverWait10.until(ExpectedConditions.visibilityOf(buttonSignIn));
        return this;
    }

    public LoginPage enterRegistrationDataIfNotNull(String userName, String email, String password) {
        if (userName != null) {
            enterTextIntoRegistrationUserNameField(userName);
        }
        if (email != null) {
            enterTextIntoRegistrationEmailField(email);
        }
        if (password != null) {
            enterTextIntoRegistrationPasswordField(password);
        }
        return this;
    }
}
