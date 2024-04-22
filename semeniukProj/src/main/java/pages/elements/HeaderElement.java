package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;

// описує елементи які є в хедері залогіненого користувача
public class HeaderElement extends CommonActionsWithElements {
    // myProfileButton - кнопка яка веде на сторінку профілю користувача
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//*[@class='text-white mr-2']")
    private WebElement userName;

    @FindBy(xpath = "//*[@class='text-white mr-2 header-search-icon']")
    private WebElement searchIcon;

    @FindBy(xpath = "//*[@class='text-white mr-2 header-chat-icon']")
    private WebElement chatIcon;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public boolean isCreatePostDisplayed() {
        return isElementDisplayed(buttonCreatePost);
    }

    public boolean isMyProfileDisplayed() {
        return isElementDisplayed(buttonMyProfile);
    }

    public boolean isUserNameDisplayed() {
        return isElementDisplayed(userName);
    }

    @Step
    public HeaderElement assertionsForLoggedInUserElementsDisplayed() {
        checkElementIsDisplayed(searchIcon);
        checkElementIsDisplayed(chatIcon);
        checkElementIsDisplayed(buttonMyProfile);
        checkElementIsDisplayed(buttonCreatePost);
        checkElementIsDisplayed(buttonSignOut);
        return this;
    }

    @Step
    public HeaderElement assertionsForLoggedInUserElementsAreNotDisplayed() {
        checkElementIsNotDisplayed(searchIcon);
        checkElementIsNotDisplayed(chatIcon);
        checkElementIsNotDisplayed(buttonMyProfile);
        checkElementIsNotDisplayed(buttonCreatePost);
        checkElementIsNotDisplayed(buttonSignOut);
        return this;
    }

    @Step
    public LoginPage clickOnSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
