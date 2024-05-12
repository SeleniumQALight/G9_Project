package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithSwitchingTab extends BaseTest {

    @Test
    public void loginTestWithSwitchingTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().closeNewTabAndSwitchToMainTab();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
    }


}
