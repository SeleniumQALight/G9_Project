package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
       checkUrlWithPattern();
        //TODO check some element that is only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage, "Success Message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText
                , actualText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
