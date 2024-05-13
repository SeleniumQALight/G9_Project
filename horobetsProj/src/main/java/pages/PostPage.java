package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement textTitle;

    @FindBy(xpath = ".//div[@class='body-content'][2]")
    private WebElement textBody;

    @FindBy(xpath = ".//div[@class='body-content'][1]")
    private WebElement textNote;

    @FindBy(xpath = ".//p[contains(text(), 'Is this post unique?')]")
    private WebElement messagePostUnique;

    @FindBy(xpath = ".//p[contains(text(), 'no')]")
    private WebElement messagePostUniqueNo;

    @FindBy(xpath = ".//p[contains(text(), 'yes')]")
    private WebElement messagePostUniqueYes;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//*[@class='svg-inline--fa fa-edit fa-w-18']")
    private WebElement buttonEditPost;

    private String textPostUniqueLocator = "//p[text()='Is this post unique? : %s']";


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    @Step
    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    @Step
    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Button Delete Post is not displayed"
                , isElementDisplayed(buttonDeletePost, "Button Delete Post"));
        Assert.assertTrue("Button Edit Post is not displayed"
                , isElementDisplayed(buttonEditPost, "Button Edit Post"));
        return this;
    }

    @Step
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage, "Success Message"));
        return this;
    }

    @Step
    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    @Step
    public PostPage checkMessageIsPostUnique() {
        Assert.assertTrue("Message 'Post is not unique' is not displayed"
                , isElementDisplayed(messagePostUnique));
        return this;
    }

    @Step
    public PostPage checkValueInMessagePostUnique(String textInPostUnique) {
        WebElement postUniqueMessage = webDriver.findElement(By.xpath(String.format(textPostUniqueLocator, textInPostUnique)));
        Assert.assertTrue("Message 'Is post unique?' is not displayed", isElementDisplayed(postUniqueMessage, "Message 'Is post unique?'"));
        return this;
    }

        @Step
    public PostPage checkTextInMessagePostUnique() {
        if (isElementDisplayed(messagePostUniqueNo)) {
            logger.info("Message 'Is this post unique? : no' is displayed");
        } else if (isElementDisplayed(messagePostUniqueYes)) {
            logger.info("Message 'Is this post unique? : yes' is displayed");
        } else {
            logger.error("Message 'Is this post unique? : no' or 'Is this post unique? : yes'  is not displayed");
            Assert.fail("Message 'Is this post unique? : no' or 'Is this post unique? : yes'  is not displayed");
        }
        return this;
    }

    @Step
    public PostPage checkTextTitleOfPost(String expectedTextTitle) {
        String actualTextTitle = textTitle.getText();
        Assert.assertEquals("Text in title", expectedTextTitle, actualTextTitle);
        return this;
    }

    @Step
    public PostPage checkTextBodyOfPost(String expectedTextBody) {
        String actualTextBody = textBody.getText();
        Assert.assertEquals("Text in body", expectedTextBody, actualTextBody);
        return this;
    }

    @Step
    public PostPage checkIsNoteDisplayed() {
        Assert.assertTrue("Note is not displayed"
                , isElementDisplayed(textNote));
        return this;
    }

    @Step
    public PostPage checkTextNoteOfPostDisplayed(String expectedTextNote) {
        String actualTextNote = textNote.getText();
        Assert.assertEquals("Text in note", expectedTextNote, actualTextNote);
        return this;
    }

    @Step
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}


