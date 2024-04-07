package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {
    @Test
    public void logOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().logOut()
                .checkIsRedirectOnLoginPage();
    }
}
