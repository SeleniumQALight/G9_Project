package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithSwitchingTabs extends BaseTest {

    @Test
    public void loginTestWithSwitchingTabs() {
       pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed()
                .openNewTab();
        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed()
                .switchToMainTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed()
                .switchToNewTab();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .closeCurrentTabAndSwitchToMain();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .getHeaderElement()
                .assertionsForLoggedInUserElementsDisplayed();


    }

}
