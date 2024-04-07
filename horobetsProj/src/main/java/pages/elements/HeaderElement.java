package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

// описує елементи, які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {

    // my profile button
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//span[contains(text(), 'qaauto')]")
    private WebElement userName;

    @FindBy(xpath = "//header//a[@class = 'text-white mr-2 header-search-icon']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;




    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
//        try {
//            boolean state = webDriver.findElement(
//                    By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed(); // знаходимо елемент
//            logger.info(state + " is button displayed");
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not visible");
//            return false;
//        }
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Button Create Post");
    }

    public boolean isButtonMyProfileDisplayed() {
        return isElementDisplayed(buttonMyProfile, "Button My Profile");
    }


    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName, "User Name");
    }

    public boolean isButtonSearchDisplayed() {
        return isElementDisplayed(buttonSearch, "Button Search");
    }


    public boolean isButtonChatDisplayed() {
        return isElementDisplayed(buttonChat,"Button Chat");
    }


    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public boolean isButtonSearchNotDisplayed() {
        return isElementNotDisplayed(buttonSearch, "Button Search");
    }

    public boolean isButtonChatNotDisplayed() {
        return isElementNotDisplayed(buttonChat, "Button Chat");
    }

    public boolean isButtonMyProfileNotDisplayed() {
        return isElementNotDisplayed(buttonMyProfile, "Button My Profile");
    }

    public boolean isButtonCreatePostNotDisplayed() {
        return isElementNotDisplayed(buttonCreatePost, "Button Create Post");
    }

    public boolean isButtonSignOutNotDisplayed() {
        return isElementNotDisplayed(buttonSignOut, "Button Sign Out");
    }
}
