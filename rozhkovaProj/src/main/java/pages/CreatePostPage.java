package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(xpath = "//input[@id= 'post-title']")
    private WebElement inputTitle;
    @FindBy(xpath = "//textarea[@id= 'post-body']")
    private WebElement textAreaBody;

    @FindBy(tagName = "select") //скорочена назва локатору xpath = "//select"
    private WebElement dropDownRole;
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy (xpath = "//input[@type='checkbox']")
    private WebElement checkBoxPostUnique;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String bodyText) {
        cleanAndEnterTextIntoElement(textAreaBody, bodyText);
        return this;
    }

    //select text in dropdown Role by visible text
    public CreatePostPage selectTextInDropDownRoleByVisibleText(String text) {
        selectTextInDropdownByVisibleText(dropDownRole, text);
        return this;
    }

    public CreatePostPage selectCheckboxPostUniqueSelected(){
        selectCheckbox(checkBoxPostUnique);
        return this;
    }

    public CreatePostPage selectCheckboxPostUniqueUnselected(){
        unselectCheckbox(checkBoxPostUnique);
        return this;
    }

    public CreatePostPage selectCheckboxPostUniqueToNeededState(){
        setCheckboxState(checkBoxPostUnique, "Check");
        return this;
    }


    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectValueInDropDownRole(String value) {
        selectValueInDropdown(dropDownRole, value);
        return this;
    }
}
