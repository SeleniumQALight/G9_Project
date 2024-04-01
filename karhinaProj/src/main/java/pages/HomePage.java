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



    public HomePage checkIsRedirectToHomePage() {
        // TODO check current URL
        Assert.assertTrue("Invalid Page Not Home Page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }






    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {

        return new CreatePostPage(webDriver);
    }
}

