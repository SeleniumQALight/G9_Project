package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div/p[contains(text(), 'Is this post unique? : yes')]")
    private WebElement isPostUnique;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']/h2")
    private WebElement titleMessage;

    @FindBy(xpath = ".//div[@class='body-content'][2]/p")
    private WebElement bodyMessage;

    @FindBy(xpath = ".//div[@class='body-content']//i[contains(text(), ' Note: This post was written for ')]")
    private WebElement noteMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PostPage checkIsRedirectOnPostPage() {
        // TODO check current URL
        // TODO check some element that is only on this page
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage, "Success message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String messageText) {
        String textFromElement = successMessage.getText();
        Assert.assertEquals("Text in message", messageText, textFromElement);
        return this;
    }

    public PostPage checkIsPostUnique() {
        Assert.assertTrue("Post is not unique", isElementDisplayed(isPostUnique, "Unique post"));
        return this;
    }

    public PostPage checkTextInTitle(String title) {
        String textFromElement = titleMessage.getText();
        Assert.assertEquals("Text in title", title, textFromElement);
        return this;
    }

    public PostPage checkTextInBody(String body) {
        String textFromElement = bodyMessage.getText();
        Assert.assertEquals("Text in body", body, textFromElement);
        return this;
    }

    public PostPage checkNoteMessage(String note) {
        String textFromElement = noteMessage.getText();
        Assert.assertEquals("Text in note", note, textFromElement);
        return this;
    }

}
