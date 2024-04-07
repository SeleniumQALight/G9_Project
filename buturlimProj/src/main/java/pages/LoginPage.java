package pages;


import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

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

    public void clickOnButtonSignIn (){
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

    public boolean isInputLoginDisplayed() {
        return isElementDisplayed(inputUserNameLoginForm, "Input Login");
    }

    public boolean isInputPasswordDisplayed() {
        return isElementDisplayed(inputPasswordLoginForm, "Input Password");
    }

    public boolean isButtonSignInNotDisplayed () {
        return isElementNotDisplayed(buttonSignIn, "Button Sign In");
    }

    public boolean isInputLoginNotDisplayed() {
        return isElementNotDisplayed(inputUserNameLoginForm, "Input Login");
    }

    public boolean isInputPasswordNotDisplayed() {
        return isElementNotDisplayed(inputPasswordLoginForm, "Input Password");
    }

    public LoginPage checkIsRedirectOnLoginPage() {
        checkUrl();
        Assert.assertTrue("Button Sign Out displayed", getHeaderElement().isButtonSignOutNotDisplayed());
        Assert.assertTrue("Search Icon displayed", getHeaderElement().isSearchIconNotDisplayed());
        Assert.assertTrue("Chat Icon displayed", getHeaderElement().isChatIconNotDisplayed());
        Assert.assertTrue("Button Create Post displayed", getHeaderElement().isButtonCreatePostNotDisplayed());
        Assert.assertTrue("My Profile img displayed", getHeaderElement().isMyProfileButtonNotDisplayed());
        Assert.assertTrue("Button Sign In is not displayed", isButtonSignInDisplayed());
        Assert.assertTrue("Input Login is not displayed", isInputLoginDisplayed());
        Assert.assertTrue("Input Password is not displayed", isInputPasswordDisplayed());
        return this;
    }

}


