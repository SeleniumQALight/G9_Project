package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class
})
public class SmokeSuite {
}
