package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{


    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(), 'Invalid username/password.')]")
    private WebElement popUp;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isPopUpDisplayed(){
        return isElementDisplayed(popUp);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }


    public HomePage checkIsRedirectToHomePage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page Not home page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }


}
