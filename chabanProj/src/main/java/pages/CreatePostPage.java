package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(xpath = ".//input[@id= 'post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id= 'post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") //скорочена форма локатора xpath = ".//select"
    private WebElement dropdownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement checkBoxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page -  Not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

public CreatePostPage enterTitleInToInputTitle(String title) {
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String text) {
      cleanAndEnterTextIntoElement(inputBody, text);
        return this;
    }

    // select text in dropdown Role by visible text
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String text) {
        selectValueInDropdownByVisibleText(dropdownRole, text);
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
    public CreatePostPage selectCheckBox() {
        checkBoxUniquePost.isSelected();
        if (!checkBoxUniquePost.isSelected()) {
            clickOnElement(checkBoxUniquePost);
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox is already selected");
        }
        return this;
    }
    public CreatePostPage deSelectCheckBox() {
        checkBoxUniquePost.isSelected();
        if (checkBoxUniquePost.isSelected()) {
            clickOnElement(checkBoxUniquePost);
            logger.info("Checkbox is deselected");
        } else {
            logger.info("Checkbox is already deselected");
        }
        return this;
    }

    public CreatePostPage settingCheckBox(String state) {
        if (state.equals("check")) {
             selectCheckBox();
            }
        if (state.equals("uncheck")) {
                deSelectCheckBox();
         }
        return this;
    }
}
