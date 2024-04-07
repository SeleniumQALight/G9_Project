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

    public boolean isMyProfileButtonDisplayed() {
        return isElementDisplayed(myProfileButton, "My Profile Button");
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut, "Button Sign Out");
    }

    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost, "Button Create Post");
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public String getProfileNameText() {
        logger.info("Profile name is: " + profileName.getText());
        return profileName.getText();
    }

    public boolean isSearchIconDisplayed() {
        return isElementDisplayed(searchIcon, "Search Icon");
    }

    public boolean isChatIconDisplayed() {
        return isElementDisplayed(chatIcon, "Chat Icon");
    }

    public boolean isButtonSignOutNotDisplayed() {
        return isElementNotDisplayed(buttonSignOut, "Button Sign Out");
    }

    public boolean isMyProfileButtonNotDisplayed() {
        return isElementNotDisplayed(myProfileButton, "My Profile Button");
    }

    public boolean isButtonCreatePostNotDisplayed() {
        return isElementNotDisplayed(buttonCreatePost, "Button Create Post");
    }

    public boolean isSearchIconNotDisplayed() {
        return isElementNotDisplayed(searchIcon, "Search Icon");
    }

    public boolean isChatIconNotDisplayed() {
        return isElementNotDisplayed(chatIcon, "Chat Icon");
    }

    public LoginPage logOut() {
        clickOnElement(buttonSignOut);
        logger.info("Button Sign Out was clicked");
        return new LoginPage(webDriver);
    }

}
