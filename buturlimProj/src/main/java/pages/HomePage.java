package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page not Home page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }


}
