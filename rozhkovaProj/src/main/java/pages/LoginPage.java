package pages;

import data.TestData;
import io.qameta.allure.Step;
import libs.DB_Util_seleniumUsers;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
//import org.openqa.selenium.By; видаляємо, бо не використовуємо
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = "//button[contains(text(),'Sign In')]") //ініціалізується в CommonActionWithElements
    private WebElement buttonSignIn;
    @FindBy(xpath = "//input [@placeholder = 'Username']")
    private WebElement inputUserNameLoginForm;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordLoginForm;
    @FindBy(id = "username-register") //xpath = "//input[@id='username-register']"
    private
    WebElement inputUserNameRegistrationForm;
    @FindBy(id = "email-register") //xpath = "//input[@id='email-register']"
    private
    WebElement inputEmailRegistrationForm;
    @FindBy(id = "password-register") //xpath = "//input[@id='password-register']"
    private
    WebElement inputPasswordRegistrationForm;

    final static String listErrorsMessagesLocator =
            "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listErrorsMessagesLocator)
    private List<WebElement> listErrorsMessages;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn, "Button Sign In");
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login Page was opened with url " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    @Step
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrlWithPattern();
        Assert.assertTrue("Invalid page Not Login page", isButtonSignInDisplayed());
        return this;
    }

    @Step
    public void enterTextIntoInputLogin(String text) {
        //WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input [@placeholder = 'Username']"));  у @FindBy
        //inputUserNameLoginForm.clear(); - приписаний метод у батьківському класі
        //inputUserNameLoginForm.sendKeys(text);//вводимо по одній літері
        // logger.info(text + " was inputted into input UserName");
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    @Step
    public void enterTextIntoInputPassword(String text) {
        //WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        //inputPasswordLoginForm.clear();
        //inputPasswordLoginForm.sendKeys(text);
        //logger.info(text + " Password was inputted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    @Step
    public void clickOnButtonSignIn() {
        // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")); у @FindBy
        //buttonSignIn.click();   //метод у батьківському прописаний
        // logger.info("Button was clicked");
        clickOnElement(buttonSignIn);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public HomePage openLoginPageAndFillLoginFormWithNewValidCredFromDB() throws SQLException, ClassNotFoundException {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_NEW_LOGIN_UI);
        DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
        String pass = dbUtilSeleniumUsers.getPassForLogin(TestData.VALID_NEW_LOGIN_UI);
        logger.info("Pass for login " + TestData.VALID_NEW_LOGIN_UI + " is " + pass);
        enterTextIntoInputPassword(pass);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        cleanAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        cleanAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorsMessages(String messages) {
        //error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrors = messages.split(";");//якщо три валідних, то масив буде з трьох елементів. якщо один невалідний, то масив буде з одного елементу

        webDriverWait_10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listErrorsMessagesLocator), expectedErrors.length));//чекаємо по локатору (бо вейт не працює зі списком, а тільки з локатором), поки кількість елементів буде рівна кількості елементів в масиві

        Util.waitABit(1);//чекаємо, поки відобразяться можливі додаткові всі меседжі

        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());//без вейтів падає тест, бо сайт не встигає завантажити всі меседжі, які мають зявитится


        ArrayList<String> actualTextMessages = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualTextMessages.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();//обєкт вміє робити перевірки накопичуючи результати. не буде зупинятися, поки самі не скажемо
        for (int i = 0; i < expectedErrors.length; i++) {// щоб не залежати від кількості елементів в масиві і порядку
            softAssertions.assertThat(actualTextMessages.get(i))
                    .as("Error " + i) //пояснення, яка помилка, яка буде виведено
                    .isIn(expectedErrors);//перевіряємо, чи є обєкт в масиві
        }//бігаємо по всіх елементах масиву і ніколи не падає тест, далі треба ассерт алл

        softAssertions.assertAll();//перевіряємо всі результати, які накопичили. Одразу виведе всі результати


        return this;
    }

    @Step
    public boolean isUserNameInputDisplayed() {
        return isElementDisplayed(inputUserNameLoginForm, "User Name input");
    }

    public LoginPage checkIsUserNameInputDisplayed() {
        checkElementIsDisplayed(inputUserNameLoginForm, "User Name input");
        return this;
    }

    @Step
    public LoginPage checkIsPasswordInputDisplayed() {
        checkElementIsDisplayed(inputPasswordLoginForm, "Password input");
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInDisplayed() {
        checkElementIsDisplayed(buttonSignIn, "Button Sign In");
        return this;
    }

    @Step
    public boolean isPasswordInputDisplayed() {
        return isElementDisplayed(inputPasswordLoginForm, "Password input");
    }

    @Step
    public LoginPage checkIsUserNameInputNotDisplayed() {
        checkElementIsNotDisplayed(inputUserNameLoginForm, "User Name input");
        return this;
    }

    @Step
    public LoginPage checkIsPasswordInputNotDisplayed() {
        checkElementIsNotDisplayed(inputPasswordLoginForm, "Password input");
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInNotDisplayed() {
        checkElementIsNotDisplayed(buttonSignIn, "Button Sign In");
        return this;
    }


}
