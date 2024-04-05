package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {


    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//*[@class ='d-flex justify-content-between']")
    private WebElement postTitle;

    @FindBy(xpath = ".//*[@class='body-content'][2]")
    private WebElement postBody;

    @FindBy(xpath = ".//div[@class='body-content']//u")
    private WebElement bodyNote;

    @FindBy(xpath = ".//*/p[contains(text(),'unique')]")
    private WebElement isPostUnique;

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
        //TODO check some element that is only on this page //dz
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(successMessage, "Success message"));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String ExpectedMessageText) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text in message is not as expected", ExpectedMessageText, actualText);
        return this;
    }
    public PostPage checkTitlesValue(String ExpectedMessageText){
        String actualTitle= postTitle.getText();
        Assert.assertEquals("Title is not as expected", ExpectedMessageText, actualTitle);
        return this;
    }
    public PostPage checkBodyValue(String ExpectedMessageText){
        String actualBody = postBody.getText();
        Assert.assertEquals("Body is not as expected", ExpectedMessageText, actualBody);
        return this;
    }

    public PostPage checkBodyNoteValue(String ExpectedMessageText){
        String actualBodyNote = bodyNote.getText();
        Assert.assertEquals("BodyNote is not as expected", ExpectedMessageText, actualBodyNote);
        return this;
    }

    public PostPage checkIsPostUnique(String ExpectedValue){
        String actualValue = isPostUnique.getText();
        Assert.assertEquals("Post is not unique", ExpectedValue, actualValue);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
