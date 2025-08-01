package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesTest extends BaseTest {
        final String ERROR_USERNAME = "Username must be at least 3 characters.";
        final String ERROR_EMAIL = "You must provide a valid email address.";
        final String ERROR_PASSWORD = "Password must be at least 12 characters.";
        final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessagesText(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"test11", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"te", "te", "te", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

        };
    }
}
