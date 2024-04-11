package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.1";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.3";
    final String SEMICOLON = ";";

    @Test
    public void validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField("Tr")
                .enterTextIntoRegistrationEmailField("notValidEmail")
                .enterTextIntoRegistrationPasswordField("notValid")
                .checkErrorsMessages(ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);



    }
}
