package pages;

import data.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
//import org.openqa.selenium.By; видаляємо, бо не використовуємо
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    private WebElement inputUserNameRegistrationForm;
    @FindBy(id = "email-register") //xpath = "//input[@id='email-register']"
    private WebElement inputEmailRegistrationForm;
    @FindBy(id = "password-register") //xpath = "//input[@id='password-register']"
    private WebElement inputPasswordRegistrationForm;

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

    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login Page was opened with url " + baseUrl);
        } catch (Exception e) {
            logger.error("Can not open Login Page " + e);
            Assert.fail("Can not open Login Page " + e);
        }
    }

    public void enterTextIntoInputLogin(String text) {
        //WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input [@placeholder = 'Username']"));  у @FindBy
        //inputUserNameLoginForm.clear(); - приписаний метод у батьківському класі
        //inputUserNameLoginForm.sendKeys(text);//вводимо по одній літері
        // logger.info(text + " was inputted into input UserName");
        cleanAndEnterTextIntoElement(inputUserNameLoginForm, text);
    }

    public void enterTextIntoInputPassword(String text) {
        //WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        //inputPasswordLoginForm.clear();
        //inputPasswordLoginForm.sendKeys(text);
        //logger.info(text + " Password was inputted");
        cleanAndEnterTextIntoElement(inputPasswordLoginForm, text);
    }

    public void clickOnButtonSignIn() {
        // WebElement buttonSignIn = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")); у @FindBy
        //buttonSignIn.click();   //метод у батьківському прописаний
        // logger.info("Button was clicked");
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        cleanAndEnterTextIntoElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        cleanAndEnterTextIntoElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        cleanAndEnterTextIntoElement(inputPasswordRegistrationForm, password);
        return this;
    }

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
                    .as("Error " + i) //пояснення, яка помилка
                    .isIn(expectedErrors);//перевіряємо, чи є в масиві
        }
        softAssertions.assertAll();//перевіряємо всі результати, які накопичили. Одразу виведе всі результати


        return this;
    }
}
