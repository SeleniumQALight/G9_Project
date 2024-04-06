package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{


    public HomePage(WebDriver webDriver) {
        super(webDriver);

    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    private  WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;

    @Override
    protected String getRelativeUrl() {
        return "/";
    }


    public HomePage checkIsRedirectToHomePage() {
       checkUrl();
        Assert.assertTrue("Invalid Page Not Home Page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()) {
            logger.info("User is already logged in.");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in.");
        }
        return this;
    }

    public boolean isInputUserNameHomePageDisplayed() {
        return isElementDisplayed(inputUserNameLoginForm);
    }

    public boolean isInputPasswordHomePageDisplayed() {
        return isElementDisplayed(inputPasswordLoginForm);
    }

    public boolean isInputUserNameHomePageIsNotDisplayed () {
        return isElementIsNotDisplayed(inputUserNameLoginForm);
    }

    public boolean isInputPasswordHomePageIsNotDisplayed () {
        return isElementIsNotDisplayed(inputPasswordLoginForm);
    }


    public boolean isButtonSignInHomePageIsNotDisplayed () {
        return !isElementDisplayed(buttonSignIn);
    }

}

