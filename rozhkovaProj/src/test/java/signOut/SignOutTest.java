package signOut;

import baseTests.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void TC_001_signOutTest_signOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().checkIsButtonSearchDisplayed()
                .checkIsButtonChatDisplayed()
                .checkIsMyProfileDisplayed()
                .checkIsButtonCreatePostDisplayed()
                .checkIsButtonSignOutDisplayed();

        pageProvider.getLoginPage()
                .checkIsUserNameInputNotDisplayed()
                .checkIsPasswordInputNotDisplayed()
                .checkIsButtonSignInNotDisplayed();

        //Assert.assertTrue("Button Search is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSearchDisplayed());
        //Assert.assertTrue("Button Chat is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonChatDisplayed());
        //Assert.assertTrue("Button My Profile is not displayed", pageProvider.getHomePage().getHeaderElement().isMyProfileDisplayed());
        //Assert.assertTrue("Button Create Post is not displayed", pageProvider.getHomePage().getHeaderElement().isCreatePostDisplayed());
        //Assert.assertTrue("Button Sign Out is not displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        //Assert.assertFalse("UserName input is displayed", pageProvider.getLoginPage().isUserNameInputDisplayed());
        //Assert.assertFalse("Password input is displayed", pageProvider.getLoginPage().isPasswordInputDisplayed());
        //Assert.assertFalse("Button Sign In is displayed", pageProvider.getLoginPage().isButtonSignInDisplayed());

        pageProvider.getHomePage()
                .getHeaderElement()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage();

        pageProvider.getHomePage()
                .getHeaderElement()
                .checkIsButtonSearchNotDisplayed()
                .checkIsButtonChatNotDisplayed()
                .checkIsMyProfileNotDisplayed()
                .checkIsButtonCreatePostNotDisplayed()
                .checkIsButtonSignOutNotDisplayed();

        pageProvider.getLoginPage()
                .checkIsUserNameInputDisplayed()
                .checkIsPasswordInputDisplayed()
                .checkIsButtonSignInDisplayed();

        /*Assert.assertFalse("Button Search is displayed", pageProvider.getHomePage().getHeaderElement().isButtonSearchDisplayed());
        Assert.assertFalse("Button Chat is displayed", pageProvider.getHomePage().getHeaderElement().isButtonChatDisplayed());
        Assert.assertFalse("Button My Profile is displayed", pageProvider.getHomePage().getHeaderElement().isMyProfileDisplayed());
        Assert.assertFalse("Button Create Post is displayed", pageProvider.getHomePage().getHeaderElement().isCreatePostDisplayed());
        Assert.assertFalse("Button Sign Out is displayed", pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("UserName input is not displayed", pageProvider.getLoginPage().isUserNameInputDisplayed());
        Assert.assertTrue("Password input is not displayed", pageProvider.getLoginPage().isPasswordInputDisplayed());
        Assert.assertTrue("Button Sign In is not displayed", pageProvider.getLoginPage().isButtonSignInDisplayed());
         */
    }

    @Test
    //Тест на перевірку разлогінування в двох табах
    //    Steps
    //     1. Open login page
    //     2. Login with valid credentials
    //     3. Check that button SignOut is visible
    //     4. Open new tab in browser using JavaScript
    //     5. Switch to new tab
    //     6. Open login page
    //     7. Check that button SignOut is visible
    //     8. Switch to main tab
    //     9. Click on button SignOut
    //     10. Check that button SignOut is not visible
    //     11. Switch to new tab
    //     12. Refresh page
    //     12. Check that button SignOut is not visible

    public void TC_002_signOutTest_signOutInTwoTabs() { //addition 3 to HW6
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage();
        pageProvider.getLoginPage().openNewTabAndSwitchToIt();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutDisplayed();
        pageProvider.getHomePage().switchToMainTab();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotDisplayed();
        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().refreshPage();
        pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutNotDisplayed();


    }

}
