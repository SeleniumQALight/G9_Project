package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = "//p[contains(text(), 'Is this post unique? : ')]")
    private WebElement postUniqueMessage;
    @FindBy(xpath = "//p[text()='Is this post unique? : yes']")
    private WebElement yesPostUniqueMessage;
    @FindBy(xpath = "//p[text()='Is this post unique? : no']")
    private WebElement noPostUniqueMessage;
    @FindBy(xpath = ".//h2")
    private WebElement postTextTitle;
    @FindBy(xpath = ".//p[text()='body text']")
    private WebElement postTextBody;
    @FindBy(xpath = "//u[contains(text(),'One Person')]")
    private WebElement postRoleOnePerson;
    @FindBy(xpath = ".//i")
    private WebElement TextThisPostWasWrittenFor;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*"; // * тому що може бути багато символів
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Step
    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check some element that must be present at this page
        return this;
    }

    @Step
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage, "Success message")); //додали назву елемента для логів
        return this;
    }

    @Step
    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();//якщо елемент знайдений по локатору, а не по тексту, то буде працювати на будь-якій мові
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    @Step
    public PostPage checkIsMessagePostUniqueDisplayed() {
        Assert.assertTrue("Message 'Is post unique?' is not displayed", isElementDisplayed(postUniqueMessage));
        return this;
    }

    @Step
    public PostPage checkValueInPostUniqueMessage() {
        if (isElementDisplayed(yesPostUniqueMessage)) {
            logger.info("Message 'Is post unique? : yes' is displayed");
        } else if (isElementDisplayed(noPostUniqueMessage)) {
            logger.info("Message 'Is post unique? : no' is displayed");
        } else {
            logger.error("Message 'Is post unique?' is not displayed");
            Assert.fail("Message 'Is post unique?' is not displayed");
        }
        return this;
    }

    @Step
    public PostPage checkValueInTitleOfPost(String expectedTextInPostTitle) {
        String actualPostTextTitle = postTextTitle.getText();
        Assert.assertEquals("Text in post title is wrong", expectedTextInPostTitle, actualPostTextTitle);
        logger.info("Text in post title is correct");
        return this;
    }

    @Step
    public PostPage checkValueInBodyOfPost(String expectedTextInPostBody) {
        String actualPostTextBody = postTextBody.getText();
        Assert.assertEquals("Text in post body is wrong", expectedTextInPostBody, actualPostTextBody);
        logger.info("Text in post body is correct");
        return this;
    }

    /* public PostPage checkValueInTitleAndBodyOfPost(String expectedTextInPostTitle, String expectedTextInPostBody) {
         String actualPostTextTitle = postTextTitle.getText();
         String actualPostTextBody = postTextBody.getText();
         Assert.assertEquals("Text in post title is wrong", expectedTextInPostTitle, actualPostTextTitle);
         Assert.assertEquals("Text in post body is wrong", expectedTextInPostBody, actualPostTextBody);
         logger.info("Texts in post title and post body are correct");
         return this;
     }*/
    @Step
    public PostPage checkIsMessageNotificationAboutOnePersonRoleDisplayed() {
        Assert.assertTrue("Message 'Note: This post was written for One Person' is not displayed", isElementDisplayed(postRoleOnePerson) && isElementDisplayed(TextThisPostWasWrittenFor));
        logger.info("Message 'Note: This post was written for One Person' is displayed");
        return this;
    }

    @Step
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}


