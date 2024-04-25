package pages.elements;

import io.qameta.allure.Step;
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

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public String getProfileNameText() {
        logger.info("Profile name is: " + profileName.getText());
        return profileName.getText();
    }

    //isDisplayed
    @Step
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut, "Button Sign Out");
    }

    @Step
    public boolean isMyProfileButtonDisplayed() {
        return isElementDisplayed(myProfileButton, "My Profile Button");
    }

    @Step
    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Button Create Post");
    }

    @Step
    public boolean isSearchIconDisplayed() {
        return isElementDisplayed(searchIcon, "Search Icon");
    }

    @Step
    public boolean isChatIconDisplayed() {
        return isElementDisplayed(chatIcon, "Chat Icon");
    }

    @Step
    public boolean isUserNameDisplayed() {
        return isElementDisplayed(profileName, "User Name");
    }

    //check is displayed

    @Step
    public HomePage checkIsButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsMyProfileButtonVisible() {
        checkIsElementVisible(myProfileButton, "My Profile Button");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsSearchIconVisible() {
        checkIsElementVisible(searchIcon, "Search Icon");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsChatIconVisible() {
        checkIsElementVisible(chatIcon, "Chat Icon");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsUserNameVisible() {
        checkIsElementVisible(profileName, "User Name");
        return new HomePage(webDriver);
    }

    //check is not displayed
    @Step
    public HomePage checkIsButtonSignOutNotVisible() {
        checkIsElementNotVisible(buttonSignOut, "Button Sign Out");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsMyProfileButtonNotVisible() {
        checkIsElementNotVisible(myProfileButton, "My Profile Button");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsButtonCreatePostNotVisible() {
        checkIsElementNotVisible(buttonCreatePost, "Button Create Post");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsSearchIconNotVisible() {
        checkIsElementNotVisible(searchIcon, "Search Icon");
        return new HomePage(webDriver);
    }

    @Step
    public HomePage checkIsChatIconNotVisible() {
        checkIsElementNotVisible(chatIcon, "Chat Icon");
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage logOut() {
        clickOnElement(buttonSignOut);
        logger.info("Button Sign Out was clicked");
        return new LoginPage(webDriver);
    }


}
