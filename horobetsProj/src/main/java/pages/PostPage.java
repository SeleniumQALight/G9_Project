package pages;

import org.junit.Assert;
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

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check current URL
        // TODO chek some element that is only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage, "Success Message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message", expectedMessageText, actualText);
        return this;
    }

    public PostPage checkMessageIsPostUnique() {
        Assert.assertTrue("Message 'Post is not unique' is not displayed"
                , isElementDisplayed(messagePostUnique));
        return this;
    }

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

    public PostPage checkTextTitleOfPost(String expectedTextTitle) {
        String actualTextTitle = textTitle.getText();
        Assert.assertEquals("Text in title", expectedTextTitle, actualTextTitle);
        return this;
    }

    public PostPage checkTextBodyOfPost(String expectedTextBody) {
        String actualTextBody = textBody.getText();
        Assert.assertEquals("Text in body", expectedTextBody, actualTextBody);
        return this;
    }

    public PostPage checkIsNoteDisplayed() {
        Assert.assertTrue("Note is not displayed"
                , isElementDisplayed(textNote));
        return this;
    }

    public PostPage checkTextNoteOfPostDisplayed(String expectedTextNote) {
        String actualTextNote = textNote.getText();
        Assert.assertEquals("Text in note", expectedTextNote, actualTextNote);
        return this;
    }


}


