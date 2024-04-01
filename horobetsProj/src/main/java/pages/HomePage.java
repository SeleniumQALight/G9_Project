package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage chekIsRedirectToHomePage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page Not Home page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }


}
