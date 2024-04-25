package pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = ".//form//input[@type='checkbox']")
    private WebElement checkboxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    @Step
    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page not Create Post page", isElementDisplayed(inputTitle, "Input Title"));
        return this;
    }

    @Step
    public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }
    @Step
    public CreatePostPage enterTextInToInputBody(String text) {
        cleanAndEnterTextIntoElement(inputBody, text);
        return this;
    }

    // select text in dropdown Role by visible text
    @Step
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String textForSelect) {
        selectTextInDropdownByVisibleText(dropdownRole, textForSelect);
        return this;
    }

    @Step
    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    @Step
    public CreatePostPage selectValueInDropdownRole(String value) {
        selectValueInDropdown(dropdownRole, value);
        return this;
    }

    @Step
    public CreatePostPage isCheckboxUniquePostChecked(String value) {
        setCheckboxState(checkboxUniquePost, value);
        return this;
    }


}
