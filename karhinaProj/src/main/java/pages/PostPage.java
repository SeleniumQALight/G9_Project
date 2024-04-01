package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique? : ')]")
    private WebElement postUniqueMessage;

    @FindBy(xpath = "//p[text()='Is this post unique? : yes']")
    private WebElement postUniqueYesMessage;

    @FindBy(xpath = "//p[text()='Is this post unique? : no']")
    private WebElement postUniqueNoMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTextTitle;

    @FindBy(xpath = ".//p[text()='body text']")
    private WebElement postTextBody;

    @FindBy(xpath = "//u[contains(text(),'One Person')]")
    private WebElement postTextForOnePerson;

    @FindBy(xpath = ".//i")
    private WebElement textNoteThisPostWasWrittenFor;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement(){
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check current URL
        //TODO check some element that is only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public PostPage checkIsMessagePostUniqueDisplayed() {
        Assert.assertTrue("Message 'Is this post unique? : ' is not displayed",
                isElementDisplayed(postUniqueMessage));
        return this;
    }

    public PostPage checkValueInMessagePostUnique(){
        if (isElementDisplayed(postUniqueYesMessage)){
            logger.info("Message 'Is this post unique? : yes' is displayed");
        } else if (isElementDisplayed(postUniqueNoMessage)){
            logger.info("Message 'Is this post unique? : no' is displayed");
        } else {
            logger.error("Message 'Is this post unique? : yes' or 'Is this post unique? : no' is not displayed");
            Assert.fail("Message 'Is this post unique? : yes' or 'Is this post unique? : no' is not displayed");
        }
        return this;
    }

    public PostPage checkValueInTitleOfPost(String expectedPostTextTitle) {
        String actualPostTextTitle = postTextTitle.getText();
        Assert.assertEquals("Incorrect text in title", expectedPostTextTitle, actualPostTextTitle);
        logger.info("Text in title is correct");
        return this;
    }

    public PostPage checkValueInBodyOfPost (String expectedTextBody) {
        String actualTextBody = postTextBody.getText();
        Assert.assertEquals("Incorrect text in body", expectedTextBody, actualTextBody);
        logger.info("Text in body is correct");
        return this;
    }

    public PostPage checkIsMessageNotificationAboutPostForOnePersonDisplayed() {
        Assert.assertTrue("Message 'This post was written for one person' is not displayed",
                isElementDisplayed(postTextForOnePerson) && isElementDisplayed(textNoteThisPostWasWrittenFor));
                logger.info("Message 'This post was written for one person' is displayed");

        return this;
    }




}


