package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest  extends BaseTest {

    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUsernameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessagesTexts(expectedMessages);

    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"mariiab1", "mb", "mb", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"bm", "bm", "bm", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }

}
