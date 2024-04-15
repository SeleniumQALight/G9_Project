package dataTests;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;

public class TestsForCheckingData extends BaseTest {
    @Test
    public void checkingOfDataDisappearingFromInputsAfterRefreshingPage() {
       pageProvider.getLoginPage().openLoginPage();
         pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            pageProvider.getLoginPage().enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            pageProvider.getLoginPage().refreshPage();
            pageProvider.getLoginPage().clickOnButtonSignIn();
            pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsNotDisplayed();


    }

}
