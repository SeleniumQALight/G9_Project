package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement TextNoteThisPostWasWrittenFor;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
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

    public PostPage checkValueInTitleAndBodyOfPost(String expectedPostTextTitle, String expectedTextBody) {
        String actualPostTextTitle = postTextTitle.getText();
        String actualTextBody = postTextBody.getText();
        Assert.assertEquals("Incorrect text in title", expectedPostTextTitle, actualPostTextTitle);
        Assert.assertEquals("Incorrect text in body", expectedTextBody, actualTextBody);
        logger.info("Text in title and body are correct");
        return this;
    }

    public PostPage checkIsMessageNotificationAboutPostForOnePersonDisplayed() {
        Assert.assertTrue("Message 'This post was written for one person' is not displayed",
                isElementDisplayed(postTextForOnePerson) && isElementDisplayed(TextNoteThisPostWasWrittenFor));
                logger.info("Message 'This post was written for one person' is displayed");

        return this;
    }




}

//u[contains(text(),'One Person')]

