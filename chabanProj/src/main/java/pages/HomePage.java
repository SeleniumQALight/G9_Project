package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//button[contains(text(),'Sign In')]")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(), 'Invalid username/password.')]")
    private WebElement popUp;

    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }
    public boolean isButtonSignInDisplayed(){
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isPopUpDisplayed(){
        return isElementDisplayed(popUp);
    }


    public HomePage checkIsRedirectToHomePage() {
        //TODO check current URL
        Assert.assertTrue("Invalid page Not home page", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
