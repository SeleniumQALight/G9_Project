package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class LoginPage extends ParentPage{
    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //ініціалізується в commonActionWithElements
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputUserNameLoginForm;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try{
            webDriver.get(baseUrl);
            logger.info("Login page was opened with URL " + baseUrl);

        }catch (Exception e){
            logger.error("Can not open login page " + e);
            Assert.fail("Can not open login page " + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);

    }
    public void enterTextIntoInputPassword(String text) {
//        WebElement inputPasswordLoginForm =
//                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//        inputPasswordLoginForm.clear();
//        inputPasswordLoginForm.sendKeys(text);
//        logger.info(text + " password was inserted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);

    }

    public void clickOnButtonSignIn(){
//        WebElement buttonSignIn =
//                webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]"));
//        buttonSignIn.click();
//        logger.info("button was clicked");
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
    public boolean isUserNameInputDisplayed(){
        return isElementDisplayed(inputUserNameLoginForm);
    }
    public boolean isPasswordInputDisplayed(){
        return isElementDisplayed(inputPasswordLoginForm);
    }
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrlWithPattern();
        //TODO check some element that is only on this page //dz
        return this;
    }
    public LoginPage assertionsForLoginPageElementsDisplayed() {
        checkElementIsDisplayed(inputUserNameLoginForm);
        checkElementIsDisplayed(inputPasswordLoginForm);
        checkElementIsDisplayed(buttonSignIn);
        return this;
    }
    public LoginPage assertionsForLoginPageElementsAreNotDisplayed() {
        checkElementIsNotDisplayed(inputUserNameLoginForm);
        checkElementIsNotDisplayed(inputPasswordLoginForm);
        checkElementIsNotDisplayed(buttonSignIn);
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
}
