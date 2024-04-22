package loginTests;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;

public class RefreshLoginPageBeforeSignIn extends BaseTest {

    @Test
    public void refreshLoginPageBeforeSignIn() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().getHeaderElement().assertionsForLoggedInUserElementsDisplayed();
    }
}