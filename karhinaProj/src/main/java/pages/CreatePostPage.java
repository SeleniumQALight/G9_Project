package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Locale;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement textAreaBody;

    @FindBy(tagName = "select") //скорочена форма локатора xpath = ".//select"
    private WebElement dropdownRole;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkbox;



    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page - not Create Post", isElementDisplayed(inputTitle));
        return this;
    }

     public CreatePostPage enterTitleIntoInputTitle(String title) {
         cleanAndEnterTextIntoElement(inputTitle, title);
         return this;
     }

     public CreatePostPage enterTextIntoInputBody(String bodyText) {
         cleanAndEnterTextIntoElement(textAreaBody, bodyText);
         return this;
     }

     //select text in dropdown Role by visible text
        public CreatePostPage selectTextInDropdownRoleByVisibleText( String text) {
            selectTextInDropdownByVisibleText(dropdownRole, text);
            return this;
        }


    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
    return new PostPage(webDriver);}


    public CreatePostPage selectValueInDropdownRole(String value) {
        selectValueInDropdown(dropdownRole, value);
        return this;
    }

   public CreatePostPage setCheckboxPostUniqueSelected () {
       setCheckboxUnselected(checkbox);
       return this;
   }

    public CreatePostPage setCheckboxPostUniqueUnselected () {
        setCheckboxSelected(checkbox);
        return this;
    }

    public CreatePostPage setCheckboxPostUniqueToNeededState(String neededState) {
        setCheckboxToNeededState(checkbox, neededState);
        return this;

    }

}