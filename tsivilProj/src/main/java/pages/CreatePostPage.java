package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

     @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement textAreaBody;

     @FindBy(tagName = "select") //скорочена форма локатора xpath = ".//select[@id='role']"
    private WebElement dropdownRole;

     @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page Not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String title) {
        //  WebElement inputTitle = webDriver.findElement(By.xpath(".//input[@id='post-title']"));
        //  inputTitle.clear();
        //  inputTitle.sendKeys(title);
        //  logger.info(title + " was inputted into input Title");
        cleanAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        //  WebElement inputUserNameLoginForm =
        //        webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        cleanAndEnterTextIntoElement(textAreaBody, bodyText);
        return this;
    }

    //select text in dropdown Role by visible text
    public CreatePostPage selectTextInDropdownRoleByVisibleText(String text) {
        //  WebElement dropdown = webDriver.findElement(By.xpath(".//select[@id='role']"));
        //  Select select = new Select(dropdown);
        //  select.selectByVisibleText(text);
        //  logger.info(text + " was selected in dropdown");
        selectTextInDropdownByVisibleText(dropdownRole, text);
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