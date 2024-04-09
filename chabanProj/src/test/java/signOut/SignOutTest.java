package signOut;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC_002_signOutTest(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed()
                .getLoginPage().assertionsForLoginPageElementsAreNotDisplayed()
                .getHeaderElement().clickOnSignOut()
                .checkIsRedirectToLoginPage()
                .assertionsForLoginPageElementsDisplayed()
                .getHeaderElement().assertionsForLoggedInUserElementsAreNotDisplayed()
        ;


    }

}
