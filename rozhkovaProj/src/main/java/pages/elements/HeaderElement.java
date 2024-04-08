package pages.elements;

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
    @FindBy (xpath = "//img[@alt='My profile']")
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
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
       /* try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }*/
        return isElementDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Create Post");
    }

    public boolean isMyProfileDisplayed() {
        return isElementDisplayed(myProfileButton, "My Profile");
    }

    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName, "User Name");
    }

    public boolean isButtonSearchDisplayed() {
        return isElementDisplayed(buttonSearch, "Button Search");
    }

    public boolean isButtonChatDisplayed() {
        return isElementDisplayed(buttonChat, "Button Chat");
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
