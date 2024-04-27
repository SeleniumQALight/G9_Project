package pages.elements;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//header//a[@class = 'text-white mr-2 header-search-icon']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;


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

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Button Create Post");
    }
    @Step
    public boolean isButtonMyProfileDisplayed() {
        return isElementDisplayed(buttonMyProfile, "Button My Profile");
    }

    @Step
    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName, "User Name");
    }
    @Step
    public boolean isButtonSearchDisplayed() {
        return isElementDisplayed(buttonSearch, "Button Search");
    }

    @Step
    public boolean isButtonChatDisplayed() {
        return isElementDisplayed(buttonChat, "Button Chat");
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
    @Step
    public HomePage checkIsButtonCreatePostDispayed() {
        checkElementsDisplayed(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsButtonMyProfileDisplayed() {
        checkElementsDisplayed(buttonMyProfile, "Button My Profile");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsUserNameDisplayed() {
        checkElementsDisplayed(userName, "User Name");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonSearchDisplayed() {
        checkElementsDisplayed(buttonSearch, "Button Search");
        return new HomePage(webDriver);

    }
    @Step
    public HomePage checkIsButtonChatDisplayed() {
        checkElementsDisplayed(buttonChat, "Button Chat");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonSignOutDisplayed() {
        checkElementsDisplayed(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonSearchNotDisplayed() {
        checkElementsNotDisplayed(buttonSearch, "Button Search");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonChatNotDisplayed() {
        checkElementsNotDisplayed(buttonChat, "Button Chat");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonMyProfileNotDisplayed() {
        checkElementsNotDisplayed(buttonMyProfile, "Button My Profile");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonCreatePostNotDisplayed() {
        checkElementsNotDisplayed(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }
    @Step
    public HomePage checkIsButtonSignOutNotDisplayed() {
        checkElementsNotDisplayed(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }
}
