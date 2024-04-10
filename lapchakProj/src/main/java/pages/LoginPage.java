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

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") // ініціалується в CommonActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(id = "username-register") // скорочений запис - (xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;


    final static String listErrorsMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
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
            logger.info("Login page was opened with URL: " + baseUrl);

        } catch (Exception e) {
            logger.error("Login page doesn't opened" + e);
            Assert.fail("Login page doesn't opened" + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoPassword(String text) {
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn() {
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
//        buttonSignIn.click();
//        logger.info("button was clicked");
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {

        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

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

    public LoginPage checkErrorsMessagesText(String messages) {
        // error1; error2; error3 - очікувані повідомлення -> [error1, error2, error3]
        String[] expectedErrors = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1); // чекаємо, поки з'являться всі додаткові меседжі

        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());
        // перевірка кожного елемента
        ArrayList<String> actualTextMessages = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualTextMessages.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(actualTextMessages.get(i)).as("Error " + i).isIn(expectedErrors);

            softAssertions.assertAll();
        }
        return this;
    }
}
