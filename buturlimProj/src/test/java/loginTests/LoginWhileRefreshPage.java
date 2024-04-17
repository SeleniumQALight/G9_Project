package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginWhileRefreshPage extends BaseTest {

    @Test

    public void loginWhileRefreshPage() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().getHeaderElement().checkIsButtonSignOutNotVisible();
    }

}
