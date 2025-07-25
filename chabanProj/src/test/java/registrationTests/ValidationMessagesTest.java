package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.User;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import static data.User.*;
import static libs.StringUtils.deleteSomeSymbols;

import static data.RegistrationValidationMessages.*;
import static data.User.SHORT_PASSWORD_NOT_VALID;
import static data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesTest extends BaseTest {


    @Test
    @Parameters(method = "parametersForValidationMessageTest")
    public void TC023_validationMessagesTest(String tcName, User user, String expectedMessages){
        logger.info(tcName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(user.getUserName())
                .enterTextIntoRegistrationEmailField(user.getEmail())
                .enterTextIntoRegistrationPasswordField(user.getPassword())
                .checkErrorsMessagesText(expectedMessages);

    }
    public Object[][] parametersForValidationMessageTest() {
        return new Object[][]{
                {"Login - valid, Email - NOT Valid, Password - NOT Valid"
                        , new User("TC023").updateEmail(User.SHORT_EMAIL_NOT_VALID).updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        ,ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email - valid, password - NOT valid", new User("TC023").updatePassword(SHORT_PASSWORD_NOT_VALID)
                        , ERROR_PASSWORD}, //automation bug -- ERROR_EMAIL + SEMICOLON + --
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(deleteSomeSymbols(USER_NAME_MIN_LENGTH, 1), SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD }, // bug app ❤️
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, SHORT_PASSWORD_NOT_VALID)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Login - valid, Email -  NOT valid, password - valid", new User(USER_NAME_MAX_LENGTH, SHORT_EMAIL_NOT_VALID, PASSWORD_MIN_LENGTH)
                        , ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password - valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH)
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL},
                {"Login -  NOT valid, Email -  NOT valid, password -  NOT valid", new User(USER_NAME_MAX_LENGTH + "1", SHORT_EMAIL_NOT_VALID, PASSWORD_MAX_LENGTH + "2".repeat(1))
                        , ERROR_LONG_USER +SEMICOLON+ ERROR_EMAIL + SEMICOLON + ERROR_LONG_PASSWORD}

//                {"taras11", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
//                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }

    }