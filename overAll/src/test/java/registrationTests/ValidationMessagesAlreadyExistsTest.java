package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.User;
import io.qameta.allure.Step;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import static data.RegistrationValidationMessages.*;


@Category(SmokeTestFilter.class)
public class ValidationMessagesAlreadyExistsTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    private  User user = new User("TC022");

    @Before
    @Step
    public void TC022_registerUser() {
//        user.setUserName(user.getUserName().toLowerCase());
//        user.setEmail(user.getEmail().toLowerCase());
        logger.info("Before method");
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                 .enterTextIntoRegistrationUserNameField(user.getUserName())
                .enterTextIntoRegistrationEmailField(user.getEmail())
                .enterTextIntoRegistrationPasswordField(user.getPassWord());
        pageProvider.getLoginPage().clickOnButtonRegistration();
        Assert.assertTrue("Button Sign Out is not displayed",
            pageProvider.getHomePage().getHeaderElement().isButtonSignOutDisplayed());
        pageProvider.getHomePage().getHeaderElement().clickOnSignOutButton()
                .checkButtonSignInDisplayed();
    }

    @Test
    @Step
    public void TC022_validationMessagesTest() {
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(user.getUserName())
                .enterTextIntoRegistrationEmailField(user.getEmail())
                .enterTextIntoRegistrationPasswordField(user.getPassWord())
                .clickOnButtonRegistration()
                .checkErrorsMessages(ERROR_USERNAME_ALREADY_EXISTS + SEMICOLON + ERROR_EMAIL_ALREADY_EXISTS);
    }



}
