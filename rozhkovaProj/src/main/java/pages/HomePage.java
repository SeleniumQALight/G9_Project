package pages;

//import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//button[text()= 'Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = "//div[text()= 'Invalid username/password.']")
    private WebElement alertMessage;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
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

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }
    public boolean isAlertMessageDisplayed() {
        return isElementDisplayed(alertMessage);
    }
}
