package pages;

import data.TestData;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") // ініціалізується в CommonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;

    @FindBy(xpath = "//div[contains(text(), 'Invalid username/password.')]")
    private WebElement popUp;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened with url " + baseUrl);
        }catch (Exception e){
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
//        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserNameLoginForm.clear();
//        inputUserNameLoginForm.sendKeys(text);
//        logger.info(text +" was inputted into input UserName");
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoInputPassword(String text){
//        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//        inputPasswordLoginForm.clear();
//        inputPasswordLoginForm.sendKeys(text);
//        logger.info(text + " password was inputted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn(){
//        WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));

//        buttonSignIn.click();
//        logger.info("button was clicked");
        clickOnElement(buttonSignIn);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }

    public boolean isPopUpDisplayed() {
        return isElementDisplayed(popUp);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);

    }
}
