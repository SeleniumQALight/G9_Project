package registrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import data.User;
import io.qameta.allure.Step;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static data.RegistrationValidationMessages.*;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesWithoutSomeFieldsTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    @Step
    public void TC021_validationMessagesTest(String caseName, User user, String expectedMessages) {
        logger.info(caseName);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterRegistrationDataIfNotNull(user.getUserName(), user.getEmail(), user.getPassword())
                .clickOnButtonSignUp();
        pageProvider.getLoginPage().checkErrorsMessagesText(expectedMessages);
    }

    @Step
    public Object[][] parametersForValidationMessagesTest() {
        return new Object[][]{
                {"Fill only Email and Password" ,new User("TC021").updateUserName(null)
                        , ERROR_USERNAME},
                {"Fill only UserName and Password", new User("TC021").updateEmail(null)
                        , ERROR_EMAIL},
                {"Fill only UserName and Email ", new User("TC021").updatePassword(null)
                        , ERROR_PASSWORD},
        };
    }


}
