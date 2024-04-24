package pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = ".//span//button[@data-original-title='Delete']")
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

    @Step
    public PostPage checkIsRedirectOnPostPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Title is not displayed", isElementDisplayed(titleMessage, "Title"));
        Assert.assertTrue("Body is not displayed", isElementDisplayed(bodyMessage, "Body"));
        Assert.assertTrue("Delete button is not displayed", isElementDisplayed(buttonDeletePost, "Delete button"));
        return this;
    }

    @Step
    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed", isElementDisplayed(successMessage, "Success message"));
        return this;
    }

    @Step
    public PostPage checkTextInSuccessMessage(String messageText) {
        String textFromElement = successMessage.getText();
        Assert.assertEquals("Text in message", messageText, textFromElement);
        return this;
    }

    @Step
    public PostPage checkIsPostUnique() {
        Assert.assertTrue("Post is not unique", isElementDisplayed(isPostUnique, "Unique post"));
        return this;
    }

    @Step
    public PostPage checkTextInTitle(String title) {
        String textFromElement = titleMessage.getText();
        Assert.assertEquals("Text in title", title, textFromElement);
        return this;
    }

    @Step
    public PostPage checkTextInBody(String body) {
        String textFromElement = bodyMessage.getText();
        Assert.assertEquals("Text in body", body, textFromElement);
        return this;
    }

    @Step
    public PostPage checkNoteMessage(String note) {
        String textFromElement = noteMessage.getText();
        Assert.assertEquals("Text in note", note, textFromElement);
        return this;
    }

    @Step
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);

    }
}
