package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@name='body']")
    private WebElement inputBody;

    @FindBy(tagName = "select")
    private WebElement dropdownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }
    public CreatePostPage enterTextInToInputBody(String text) {
        cleanAndEnterTextIntoElement(inputBody, text);
        return this;
    }

    // select text in dropdown Role by visible text
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String textForSelect) {
        selectTextInDropdownByVisibleText(dropdownRole, textForSelect);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueInDropdownRole(String value) {
        selectValueInDropdown(dropdownRole, value);
        return this;
    }
}
