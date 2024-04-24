package pages.elements;

import io.qameta.allure.Step;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

//описує елементи сторінки, які відносяться до заголовка залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {
    //myProfileButton - кнопка, яка відкриває меню з профілем користувача
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement myProfileButton;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;
    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;
    @FindBy(xpath = "//header//a[@class = 'text-white mr-2 header-search-icon']")
    private WebElement buttonSearch;
    @FindBy(xpath = "//header//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSignOutDisplayed() {
       /* try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }*/
        return isElementDisplayed(buttonSignOut, "Sign Out");
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public boolean isCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Create Post");
    }

    @Step
    public boolean isMyProfileDisplayed() {
        return isElementDisplayed(myProfileButton, "My Profile");
    }

    @Step
    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName, "User Name");
    }

    public boolean isButtonSearchDisplayed() {
        return isElementDisplayed(buttonSearch, "Button Search");
    }

    public boolean isButtonChatDisplayed() {
        return isElementDisplayed(buttonChat, "Button Chat");
    }

    @Step
    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderElement checkIsButtonSearchDisplayed() {
        checkElementIsDisplayed(buttonSearch, "Button Search");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatDisplayed() {
        checkElementIsDisplayed(buttonChat, "Button Chat");
        return this;
    }

    @Step
    public HeaderElement checkIsMyProfileDisplayed() {
        checkElementIsDisplayed(myProfileButton, "My Profile");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostDisplayed() {
        checkElementIsDisplayed(buttonCreatePost, "Create Post");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutDisplayed() {
        checkElementIsDisplayed(buttonSignOut, "Sign Out");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSearchNotDisplayed() {
        checkElementIsNotDisplayed(buttonSearch, "Button Search");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonChatNotDisplayed() {
        checkElementIsNotDisplayed(buttonChat, "Button Chat");
        return this;
    }

    @Step
    public HeaderElement checkIsMyProfileNotDisplayed() {
        checkElementIsNotDisplayed(myProfileButton, "My Profile");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonCreatePostNotDisplayed() {
        checkElementIsNotDisplayed(buttonCreatePost, "Create Post");
        return this;
    }

    @Step
    public HeaderElement checkIsButtonSignOutNotDisplayed() {
        checkElementIsNotDisplayed(buttonSignOut, "Sign Out");
        return this;
    }
}
