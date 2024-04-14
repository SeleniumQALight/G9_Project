package dataTests;

import baseTest.BaseTest;
import org.junit.Test;

public class TestsForCheckingData extends BaseTest {
    @Test
    public void checkingOfDataDisappearingFromInputsAfterRefreshingPage() {
       pageProvider.getLoginPage().openLoginPage();
         pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
            pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
            pageProvider.getLoginPage().refreshPage();
            pageProvider.getLoginPage().clickOnButtonSignIn();
            pageProvider.getHomePage().getHeaderElement().checkIsButtonSignOutIsNotDisplayed();


    }

}
