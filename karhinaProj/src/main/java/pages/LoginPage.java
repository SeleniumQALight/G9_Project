package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //Ініціалізується в Common ActionsWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
        private  WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement massageInvalidUsernamePassword;


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
            logger.info("Login Page was opened with URL " + baseUrl);
        } catch (Exception e){
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void  enterTextIntoPassword(String text){
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn(){
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed() {return isElementDisplayed(buttonSignIn);
    }

    public boolean isInvalidUsernamePasswordDisplayed() {
        return isElementDisplayed(massageInvalidUsernamePassword);
    }


    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkIsRedirectToLoginPage() {
        checkUrl();
        Assert.assertTrue("Invalid Page Not Home Page", isButtonSignInDisplayed());
        return this;
    }


    public boolean checkIsInputUserNameDisplayed() {
        return checkElementIsDisplayed(inputUserNameLoginForm);
    }

    public boolean checkIsInputPasswordDisplayed() {
        return checkElementIsDisplayed(inputPasswordLoginForm);
    }

    public boolean checkIsButtonSignInDisplayed() {
        return checkElementIsDisplayed(buttonSignIn);
    }
}

