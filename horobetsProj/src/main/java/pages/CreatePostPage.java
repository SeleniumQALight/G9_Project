package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = "//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(tagName = "select") // скорочена форма локатора xpath = ".//select"
    private WebElement dropdownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy (xpath = "//input[@type='checkbox']")
    private WebElement checkbox;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }
    @Step
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Create Post Page"
                , isElementDisplayed(inputTitle));
        return this;
    }
    @Step
    public CreatePostPage enterTitleIntoInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }
    @Step
    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        cleanAndEnterTextIntoElement(textAreaBody, bodyText);
        return this;
    }
    @Step
    public CreatePostPage setCheckboxStateCheckOrUncheck(String state){
        setCheckboxState(checkbox, state);
        return this;
    }
    @Step
    // select text in dropdown Role by visible text
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String text) {
        selectTextInDropdownByVisibleText(dropdownRole, text);
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
}
