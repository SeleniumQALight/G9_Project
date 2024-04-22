package Suits;

import Categories.SmokeTestFilter;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class
})
public class SmokeSuite {
}
