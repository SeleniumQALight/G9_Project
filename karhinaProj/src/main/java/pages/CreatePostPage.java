package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Locale;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page - not Create Post", inputTitle.isDisplayed());
        return this;
    }
}
