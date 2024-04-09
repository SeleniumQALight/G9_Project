package pages;

import data.TestData;
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

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") // ініціалізується в CommonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = "//div[contains(text(), 'Invalid username/password.')]")
    private WebElement popUp;

    @FindBy(id = "username-register")   //xpath = "//input[@placeholder='Username']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")   //xpath = "//input[@placeholder='Email']"
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listErrorsMessagesLocator =
            "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listErrorsMessages;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened with url " + baseUrl);
        }catch (Exception e){
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
//        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserNameLoginForm.clear();
//        inputUserNameLoginForm.sendKeys(text);
//        logger.info(text +" was inputted into input UserName");
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoInputPassword(String text){
//        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//        inputPasswordLoginForm.clear();
//        inputPasswordLoginForm.sendKeys(text);
//        logger.info(text + " password was inputted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn(){
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));

//        buttonSignIn.click();
//        logger.info("button was clicked");
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isPopUpDisplayed() {
        return isElementDisplayed(popUp);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }

    public boolean isInputLoginDisplayed() {
        return isElementDisplayed(inputUserNameLoginForm);
    }

    public boolean isInputPasswordDisplayed() {
        return isElementDisplayed(inputPasswordLoginForm);
    }




    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid page Not Login page", isButtonSignInDisplayed());
        return this;
    }

    public LoginPage checkIsInputLoginNotDispayed() {
        checkElementsNotDisplayed(inputUserNameLoginForm, "Input Login");
        return this;
    }

    public LoginPage checkIsInputPasswordNotDispayed() {
        checkElementsNotDisplayed(inputPasswordLoginForm, "Input Password");
        return this;
    }

    public LoginPage checkIsButtonSignInNotDisplayed() {
        checkElementsNotDisplayed(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage checkIsInputLoginDispayed() {
        checkElementsDisplayed(inputUserNameLoginForm, "Input Login");
        return this;
    }

    public LoginPage checkIsInputPasswordDispayed() {
        checkElementsDisplayed(inputPasswordLoginForm, "Input Password");
        return this;
    }

    public LoginPage checkIsButtonSignInDisplayed() {
        checkElementsDisplayed(buttonSignIn, "Button Sign In");
        return this;
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
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


    public LoginPage checkErrorsMessages(String messages) {
        // error1; error2; error3; -> [error1, error2, error3]
        String[] expectedErrors = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length ));

        Util.waitABit(1);

        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

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
