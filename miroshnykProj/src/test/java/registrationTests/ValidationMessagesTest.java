package registrationTests;

import baseTest.BaseTest;
import com.beust.jcommander.Parameters;
import org.junit.Test;
import org.junit.runners.Parameterized;

public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.1";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.3";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametrForValidationMessagesTest");
    public void validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField("Tr")
                .enterTextIntoRegistrationEmailField("notValidEmail")
                .enterTextIntoRegistrationPasswordField("notValid")
                .checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);



    }
}
