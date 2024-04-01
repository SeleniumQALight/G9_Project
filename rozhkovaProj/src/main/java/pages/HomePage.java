package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    @FindBy(xpath = "//button[text()= 'Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = "//div[text()= 'Invalid username/password.']")
    private WebElement alertMessage;





    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page Not Home page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isAlertMessageDisplayed() {
        return isElementDisplayed(alertMessage);
    }
}
