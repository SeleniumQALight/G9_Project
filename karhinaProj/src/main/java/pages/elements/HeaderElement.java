package pages.elements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

public class HeaderElement extends CommonActionsWithElements_new {

    //описує елементи, які є в хедері залогіненого користувача

    //myProfile button

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//span[contains(text(), '%s')]")
    private WebElement userName;
    @FindBy(xpath = "//a[@data-original-title = 'Search']")
    private WebElement buttonSearch;
    @FindBy(xpath = "//span[@data-original-title = 'Chat']")
    private WebElement buttonChat;

    private String userNameLocator = ".//span[contains(text(), '%s')]";


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    @Step
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }
    @Step
    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost);
    }
    @Step
    public boolean isButtonMyProfileDisplayed() {
        return isElementDisplayed(buttonMyProfile);
    }
    @Step
    public boolean checkIsButtonSignOutDisplayed() {
        return checkElementIsDisplayed(buttonSignOut);
    }
    @Step
    public boolean checkIsButtonCreatePostDisplayed() {
        return checkElementIsDisplayed(buttonCreatePost);
    }
    @Step
    public boolean checkIsButtonMyProfileDisplayed() {
        return checkElementIsDisplayed(buttonMyProfile);
    }


    private WebElement getUserName(String userName) {
        String locator = String.format(userNameLocator, userName);
        return webDriver.findElement(By.xpath(locator));
    }
    @Step
    public boolean checkIsUsernameIsPresent(String userName) {
        Assert.assertTrue("Username is not displayed", isElementDisplayed(getUserName(userName)));
        return true;
    }
    @Step
    public boolean checkIsButtonSearchDisplayed() {
        return checkElementIsDisplayed(buttonSearch);
    }
    @Step
    public boolean checkIsButtonChatDisplayed() {
        return checkElementIsDisplayed(buttonChat);
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
    @Step
    public boolean checkIsButtonSearchIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonSearch);
    }
    @Step
    public boolean checkIsButtonChatIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonChat);
    }
    @Step
    public boolean checkIsButtonMyProfileIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonMyProfile);
    }
    @Step
    public boolean checkIsButtonCreatePostIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonCreatePost);
    }
    @Step
    public boolean checkIsButtonSignOutIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonSignOut);
    }
    @Step
    public boolean checkIsUsernameIsNotPresent(String userName) {
        Assert.assertFalse("Username is displayed", isElementDisplayed(getUserName(userName)));
        return true;
    }


}
