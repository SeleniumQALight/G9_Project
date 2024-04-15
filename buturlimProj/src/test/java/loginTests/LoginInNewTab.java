package loginTests;

import baseTest.BaseTest;
import org.junit.Test;


public class LoginInNewTab extends BaseTest {

    @Test
    public void loginInNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().switchToOldTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().closeAllTabsAndSwitchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutVisible();
    }
}
