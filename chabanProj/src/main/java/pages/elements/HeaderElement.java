package pages.elements;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

//описуємо елементи які будуть в хедері !залогіненого! юзера
public class HeaderElement extends CommonActionWithElements {
    //My Profile Button

    @FindBy(xpath = ".//img[@alt='My profile']")
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

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //ініціалізується в commonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }

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


    public HeaderElement assertionsForLoggedInUserElementsDisplayed() {
        checkElementIsDisplayed(searchIcon);
        checkElementIsDisplayed(chatIcon);
        checkElementIsDisplayed(buttonMyProfile);
        checkElementIsDisplayed(buttonCreatePost);
        checkElementIsDisplayed(buttonSignOut);
        return this;
    }

    public HeaderElement assertionsForLoggedInUserElementsAreNotDisplayed() {
        checkElementIsNotDisplayed(searchIcon);
        checkElementIsNotDisplayed(chatIcon);
        checkElementIsNotDisplayed(buttonMyProfile);
        checkElementIsNotDisplayed(buttonCreatePost);
        checkElementIsNotDisplayed(buttonSignOut);
        return this;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }
    public LoginPage clickOnSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
