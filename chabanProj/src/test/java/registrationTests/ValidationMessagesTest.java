package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    public void validationMessagesTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("notValid")
                .enterTextIntoRegistrationPasswordField("1234")
                .checkErrorsMessagesText(ERROR_EMAIL + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);

    }
}
