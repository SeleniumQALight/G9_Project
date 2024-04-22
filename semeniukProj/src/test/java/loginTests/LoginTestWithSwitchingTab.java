package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithSwitchingTab extends BaseTest {

    @Test
    public void loginTestWithSwitchingTab() {
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
