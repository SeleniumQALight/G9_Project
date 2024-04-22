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

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique? : yes')]")
    private WebElement postUniqueMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique? : no')]")
    private WebElement postIsNotUniqueMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTextTitle;

    @FindBy(xpath = "//p[text()='body text']")
    private WebElement postBodyText;

    @FindBy(xpath = "//u[contains(text(), 'One Person')]")
    private WebElement postNoteOnePerson;

    @FindBy(xpath = ".//i")
    private WebElement postNoteWasWrittenFor;

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


    public PostPage checkIsRedirectedToPostPage() {
        checkUrlWithPattern();
        // TODO check is element displayed
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage, "Success message"));
        // TODO check is element displayed
        return this;
    }


    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        // TODO check is element displayed
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }


    public PostPage checkIsPostUnique() {
        if (isElementDisplayed(postUniqueMessage)) {
            Assert.assertTrue("Post is not unique", isElementDisplayed(postUniqueMessage));
            logger.info("Post is unique");
        } else {
            Assert.assertTrue("Post is unique", isElementDisplayed(postIsNotUniqueMessage));
            logger.info("Post is not unique");
        }
        return this;
    }

    public PostPage checkIsPostTitle(String expectedPostTitle) {
        String actualPostTitle = postTextTitle.getText();
        Assert.assertEquals("Wrong Post title", expectedPostTitle, actualPostTitle);
        logger.info("Correct Post title");
        return this;
    }

    public PostPage checkPostBody(String expectedPostBody) {
        String actualPostBody = postBodyText.getText();
        Assert.assertEquals("Wrong Post body", expectedPostBody, actualPostBody);
        logger.info("Correct Post body");
        return this;
    }

    public PostPage checkPostNote() {
        Assert.assertTrue("Post Note is not for one person", isElementDisplayed(postNoteOnePerson) && isElementDisplayed(postNoteWasWrittenFor));
        logger.info("Post Note is for one person");
        return this;
    }
}
