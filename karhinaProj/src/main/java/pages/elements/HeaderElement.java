package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

public class HeaderElement extends СommonActionsWithElements {

    //описує елементи, які є в хедері залогіненого користувача

    //myProfile button

    @FindBy (xpath = "//img[@alt='My profile']")
            private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//span[contains(text(), 'qaauto')]")
    private WebElement userName;
    @FindBy(xpath = "//a[@data-original-title = 'Search']")
    private WebElement buttonSearch;
    @FindBy(xpath = "//span[@data-original-title = 'Chat']")
    private WebElement buttonChat;




    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage (webDriver);
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

    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName);
    }

    public boolean isButtonSearchDisplayed() {
        return isElementDisplayed(buttonSearch);
    }
    public boolean isButtonChatDisplayed() {
        return isElementDisplayed(buttonChat);
    }


    public boolean isButtonCreatePostNotDisplayed() {
        return isElementIsNotDisplayed(buttonCreatePost);
    }

    public boolean isButtonMyProfileNotDisplayed() {
        return isElementIsNotDisplayed(buttonMyProfile);
    }

    public boolean isButtonSearchNotDisplayed() {
        return isElementIsNotDisplayed(buttonSearch);
    }
    public boolean isButtonChatNotDisplayed() {
        return isElementIsNotDisplayed(buttonChat);
    }

    public boolean isButtonSignOutNotDisplayed() {
        return isElementIsNotDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
