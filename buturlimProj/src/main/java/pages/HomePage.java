package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
 @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
 private WebElement buttonSignOut;

 @FindBy(xpath = "//a[contains(text(),'Create Post')]")
 private WebElement buttonCreatePost;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
//            logger.info(state + " is button displayed");
//            return state;
//        } catch (Exception e) {
//            logger.info("Element isnt visible");
//            return false;
//        }
       return isElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsRedirectOnHomePage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page not Home page", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
