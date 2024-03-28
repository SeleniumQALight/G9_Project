package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check current URL
        //TODO check some element that is only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText
                , actualText);
        return this;
    }
}
