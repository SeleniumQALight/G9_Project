package loginTests;

import baseTest.BaseTest;
import org.junit.Test;


public class LoginTestWithPageObject extends BaseTest {
    final String VALID_LOGIN = "qaauto";
    final String VALID_PASSWORD = "123456qwerty";

    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN);
        pageProvider.getLoginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectOnHomePage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonCreatePostVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsMyProfileButtonVisible();
        pageProvider.getHomePage().getHeaderElement().checkIsUserNameVisible();

    }
}
