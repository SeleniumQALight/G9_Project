package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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
//        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//        inputUserNameLoginForm.clear();
//        inputUserNameLoginForm.sendKeys(text);
//        logger.info(text + " was inputted into input Username");
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoInputPassword(String text) {
//        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//        inputPasswordLoginForm.clear();
//        inputPasswordLoginForm.sendKeys(text);
//        logger.info(text + " was inputted into input Password");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn (){
//        WebElement buttonSignIn = webDriver.findElement(By.xpath(".//button[contains(text(), 'Sign In')]"));
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    public boolean isLoginErrorDisplayed(){
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
}


