package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[contains(text(), 'Invalid username/password.')]")
    private WebElement popUp;


    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page is not Home page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isPopUpDisplayed(){
        return isElementDisplayed(popUp);
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()){
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User was logged in");
        }
        return this;
    }
}
