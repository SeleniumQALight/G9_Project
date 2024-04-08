package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement textareaBody;

    @FindBy(tagName = "select") // скорочена форма локатора xpath = ".//select"
    private WebElement dropDownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page Not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        cleanAndEnterTextIntoElement(textareaBody, bodyText);
        // TODO implement method to enter text in to textarea
        return this;
    }

    // select text in dropdown role by visible text
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String text) {
        selectTextInDropdownByVisibleText(dropDownRole, text);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueInDropdownRole(String value) {
        selectValueInDropdown(dropDownRole, value);
        return this;
    }
}
