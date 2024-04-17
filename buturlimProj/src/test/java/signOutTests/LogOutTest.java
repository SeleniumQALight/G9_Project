package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {
    @Test
    public void logOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage();
        pageProvider.getHomePage().getHeaderElement().checkIsSearchIconVisible()
                .getHeaderElement().checkIsChatIconVisible()
                .getHeaderElement().checkIsMyProfileButtonVisible()
                .getHeaderElement().checkIsButtonCreatePostVisible()
                .getHeaderElement().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().checkIsInputLoginNotVisible()
                .checkIsInputPasswordNotVisible()
                .checkIsButtonSignInNotVisible();
        pageProvider.getHomePage().getHeaderElement().logOut()
                .getHeaderElement().checkIsSearchIconNotVisible()
                .getHeaderElement().checkIsChatIconNotVisible()
                .getHeaderElement().checkIsMyProfileButtonNotVisible()
                .getHeaderElement().checkIsButtonCreatePostNotVisible()
                .getHeaderElement().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsRedirectOnLoginPage()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();
    }
}
