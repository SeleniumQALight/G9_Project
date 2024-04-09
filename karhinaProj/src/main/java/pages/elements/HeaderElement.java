package pages.elements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

import java.util.List;

public class HeaderElement extends СommonActionsWithElements {

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

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileDisplayed() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean checkIsButtonSignOutDisplayed() {
        return checkElementIsDisplayed(buttonSignOut);
    }

    public boolean checkIsButtonCreatePostDisplayed() {
        return checkElementIsDisplayed(buttonCreatePost);
    }

    public boolean checkIsButtonMyProfileDisplayed() {
        return checkElementIsDisplayed(buttonMyProfile);
    }


    private WebElement getUserName(String userName) {
        String locator = String.format(userNameLocator, userName);
        return webDriver.findElement(By.xpath(locator));
    }

    public boolean checkIsUsernameIsPresent(String userName) {
        Assert.assertTrue("Username is not displayed", isElementDisplayed(getUserName(userName)));
        return true;
    }

    public boolean checkIsButtonSearchDisplayed() {
        return checkElementIsDisplayed(buttonSearch);
    }

    public boolean checkIsButtonChatDisplayed() {
        return checkElementIsDisplayed(buttonChat);
    }


    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean checkIsButtonSearchIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonSearch);
    }

    public boolean checkIsButtonChatIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonChat);
    }

    public boolean checkIsButtonMyProfileIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonMyProfile);
    }

    public boolean checkIsButtonCreatePostIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonCreatePost);
    }
    public boolean checkIsButtonSignOutIsNotDisplayed() {
        return checkElementIsNotDisplayed(buttonSignOut);
    }

    public boolean checkIsUsernameIsNotPresent(String userName) {
        Assert.assertFalse("Username is displayed", isElementDisplayed(getUserName(userName)));
        return true;
    }


}
