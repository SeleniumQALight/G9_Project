package signOut;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.PageProvider;

public class SignOutTest extends BaseTest {

    @Test
    public void TC_002_signOutTest(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed();
        pageProvider.getLoginPage()
                .assertionsForLoginPageElementsAreNotDisplayed();
        pageProvider.getHomePage().getHeaderElement().clickOnSignOut()
                .checkIsRedirectToLoginPage()
                .assertionsForLoginPageElementsDisplayed();
        pageProvider.getHomePage().getHeaderElement()
                .assertionsForLoggedInUserElementsAreNotDisplayed();
    }

}