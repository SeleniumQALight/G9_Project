package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

// описуємо елементи які є в хедері залогіненого юзера
public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[contains(text(),'Create Post')]")
    private WebElement buttonCreatePost;

    // myProfile button
    @FindBy(xpath = ".//header//img[@alt=\"My profile\"]")
    private WebElement myProfileButton;

    @FindBy(xpath = ".//header//span[@class='text-white mr-2']")
    private WebElement profileName;

    @FindBy(xpath = ".//header//a[@class='text-white mr-2 header-search-icon']")
    private WebElement searchIcon;

    @FindBy(xpath = ".//header//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatIcon;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public String getProfileNameText() {
        logger.info("Profile name is: " + profileName.getText());
        return profileName.getText();
    }

    //isDisplayed
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut, "Button Sign Out");
    }

    public boolean isMyProfileButtonDisplayed() {
        return isElementDisplayed(myProfileButton, "My Profile Button");
    }

    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Button Create Post");
    }

    public boolean isSearchIconDisplayed() {
        return isElementDisplayed(searchIcon, "Search Icon");
    }

    public boolean isChatIconDisplayed() {
        return isElementDisplayed(chatIcon, "Chat Icon");
    }

    public boolean isUserNameDisplayed() {
        return isElementDisplayed(profileName, "User Name");
    }

    //check is displayed

    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }

    public HomePage checkIsMyProfileButtonVisible() {
        checkIsElementVisible(myProfileButton, "My Profile Button");
        return new HomePage(webDriver);
    }

    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }

    public HomePage checkIsSearchIconVisible() {
        checkIsElementVisible(searchIcon, "Search Icon");
        return new HomePage(webDriver);
    }

    public HomePage checkIsChatIconVisible() {
        checkIsElementVisible(chatIcon, "Chat Icon");
        return new HomePage(webDriver);
    }

    public HomePage checkIsUserNameVisible() {
        checkIsElementVisible(profileName, "User Name");
        return new HomePage(webDriver);
    }

    //check is not displayed
    public HomePage isButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }

    public HomePage isMyProfileButtonNotVisible() {
        checkIsElementNotVisible(myProfileButton, "My Profile Button");
        return new HomePage(webDriver);
    }

    public HomePage isButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }

    public HomePage isSearchIconNotVisible() {
        checkIsElementNotVisible(searchIcon, "Search Icon");
        return new HomePage(webDriver);
    }

    public HomePage isChatIconNotVisible() {
        checkIsElementNotVisible(chatIcon, "Chat Icon");
        return new HomePage(webDriver);
    }


    public LoginPage logOut() {
        clickOnElement(buttonSignOut);
        logger.info("Button Sign Out was clicked");
        return new LoginPage(webDriver);
    }


}
