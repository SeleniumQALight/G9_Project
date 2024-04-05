package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;
import pages.elements.MyProfilePage;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']")
    private WebElement postTitle;

    @FindBy(xpath = "//div[@class='body-content' and not (descendant::i)]")
    private WebElement postBody;

    @FindBy(xpath = "//div[@class='body-content']//u")
    private WebElement onePersonLocator;

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
        // TODO check some element that can be only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage, "Success message"));
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
