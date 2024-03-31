package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']")
    private WebElement postTitle;

    @FindBy(xpath = "//div[@class='body-content' and not (descendant::i)]")
    private WebElement postBody;

    @FindBy(xpath = "//div[@class='body-content']//u")
    private WebElement onePersonLocator;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO checkCurrentUrl
        // TODO check some element that can be only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed",
                isElementDisplayed(successMessage));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public PostPage checkYourTitleIsPostWasAdded(String expectedPostTitle) {
        String actualText = postTitle.getText();
        Assert.assertEquals("Text in title", expectedPostTitle, actualText);
        logger.info("Correct title is present");
        return this;
    }

    public PostPage checkYourBodyIsPostWasAdded(String expectedBodyText) {
        String actualText = postBody.getText();
        Assert.assertEquals("Expected body text", expectedBodyText, actualText);
        logger.info("Correct body text is present");
        return this;
    }

    public PostPage checkTheNoteAboutOnePersonIsPresent(String onePerson) {
        boolean isPresent = onePersonLocator.getText().contains(onePerson);
        Assert.assertTrue("Text is not present", isPresent);
        logger.info("Message about one person is present");
        return this;
    }
}
