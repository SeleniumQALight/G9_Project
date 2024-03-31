package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

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

    public HomePage chekIsRedirectToHomePage() {
        // TODO check current URL
        Assert.assertTrue("Invalid page Not Home page", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
