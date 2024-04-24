package loginTests;

import baseTests.BaseTest;
import data.TestData;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void TC_001_loginTest_validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertFalse("Button Sign In is displayed", pageProvider.getLoginPage().isButtonSignInDisplayed());

        Assert.assertTrue("Button Create Post is not displayed", pageProvider.getHomePage().getHeaderElement().isCreatePostDisplayed());
        Assert.assertTrue("Button My Profile is not displayed", pageProvider.getHomePage().getHeaderElement().isMyProfileDisplayed());
        Assert.assertTrue("User Name is not displayed", pageProvider.getHomePage().getHeaderElement().isUserNameDisplayed());
        Assert.assertFalse("User Name input is displayed", pageProvider.getLoginPage().isUserNameInputDisplayed());
        Assert.assertFalse("Password input is displayed", pageProvider.getLoginPage().isPasswordInputDisplayed());
    }

    @Test
    public void TC_001_01_loginTest_validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");//ств змінну, яка містить мапу з даними з екселя
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.getLoginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    public void TC_002_loginTest_notValidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto22");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In is not visible", pageProvider.getLoginPage().isButtonSignInDisplayed());
        Assert.assertTrue("Alert message is not visible", pageProvider.getHomePage().isAlertMessageDisplayed());
    }

    @Test
    //Написати тест на перевірка того, що при відкритті сайту в новій вкладці (javaScript-ом)
    //залогіненим юзером ми одразу залогінені. (перевірити, що кнопка SignOut видима)
    // також перевірити що при закритті нової вкладки ми залишаємось залогіненими
    //     Steps:
    //     1. Open login page
    //     2. Login with valid credentials
    //     3. Check that button SignOut is visible
    //     4. Open new tab in browser using JavaScript
    //     5. Switch to new tab
    //     6. Open login page
    //     7. Check that button SignOut is visible
    //     8. Switch to main tab
    //     9. Check that button SignOut is visible
    //     10. Close new tab and switch to main tab
    //     11. Check that button SignOut is visible


    public void TC_003_loginTest_loginInNewTab() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage().openNewTabAndSwitchToIt();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().closeActiveTabAndSwitchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
    }

    @Test
    //Написати тест на перевірку що при рефреші сторінки введені данні в інпути "зникають" .
    // Наприклад так - вводимо данні , рефрешим сторінку і перевіряємо що не залогінилися
    //Steps:
    //    1. Open login page
    //    2. Enter 'qaauto' login into input Login
    //    3. Enter '123456qwerty' password into input Password
    //    4. Refresh page
    //    5. Click on button SingIn
    //    6. Check that button SignOut is not visible

    public void TC_004_loginTest_inputDataDisappearsAfterRefresh() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button Sign Out is displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }

    @Test
    //Тест на валідний логін за допомогою кнопок (ТАБ і Ентер) використувати Actions клас
    //    Steps
    //    1. Open login page
    //    2. Press Tab key
    //    3. Press Tab key
    //    4. Enter login into input Login (введення без елемента, використовуючи класс Actions)
    //    5. Press Tab key
    //    6. Enter password into input Password
    //    7. Press Enter key
    //    8. Check that button SignOut is visible

    public void TC_005_loginTest_validLoginUsingTabAndEnter() { //addition 1 to HW6
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().pressTabKey(2);
        pageProvider.getLoginPage().enterTextIntoField(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().pressTabKey(1);
        pageProvider.getLoginPage().enterTextIntoField(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().pressEnterKey();
        Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
    }




}
