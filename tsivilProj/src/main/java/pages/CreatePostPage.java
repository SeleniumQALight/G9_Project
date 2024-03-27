package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
 @FindBy(xpath =".//input[@id='post-title']")
 private WebElement inputTitle;
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        // TODO checkCurrentUrl
        Assert.assertTrue("Invalid page Not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }
}
