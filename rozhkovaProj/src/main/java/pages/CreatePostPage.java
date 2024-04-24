package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = "//input[@id= 'post-title']")
    private WebElement inputTitle;
    @FindBy(xpath = "//textarea[@id= 'post-body']")
    private WebElement textAreaBody;

    @FindBy(tagName = "select") //скорочена назва локатору xpath = "//select"
    private WebElement dropDownRole;
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxPostUnique;


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
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    @Step
    public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    @Step
    public CreatePostPage enterTextInToInputBody(String bodyText) {
        cleanAndEnterTextIntoElement(textAreaBody, bodyText);
        return this;
    }

    //select text in dropdown Role by visible text
    @Step
    public CreatePostPage selectTextInDropDownRoleByVisibleText(String text) {
        selectTextInDropdownByVisibleText(dropDownRole, text);
        return this;
    }

    @Step
    public CreatePostPage selectCheckboxPostUniqueSelected() {
        selectCheckbox(checkBoxPostUnique);
        return this;
    }

    @Step
    public CreatePostPage selectCheckboxPostUniqueUnselected() {
        unselectCheckbox(checkBoxPostUnique);
        return this;
    }

    @Step
    public CreatePostPage selectCheckboxPostUniqueToNeededState(String neededState) {
        setCheckboxState(checkBoxPostUnique, neededState);
        return this;
    }

    @Step
    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    @Step
    public CreatePostPage selectValueInDropDownRole(String value) {
        selectValueInDropdown(dropDownRole, value);
        return this;
    }
}
