package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import registrationTests.ValidationMessagesAlreadyExistsTest;
import registrationTests.ValidationMessagesTest;
import registrationTests.ValidationMessagesWithoutSomeFieldsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidationMessagesTest.class,
        ValidationMessagesAlreadyExistsTest.class,
        ValidationMessagesWithoutSomeFieldsTest.class,
        LoginTestWithPageObject.class
})
public class RegistrationAndLoginFormSuite {
}
