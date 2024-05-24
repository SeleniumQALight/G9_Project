package suits;


import apiTests.ApiTests;
import catagories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        loginTests.LoginTestWithPageObject.class,
        ValidationMessagesTest.class,
        CreateNewPostTest.class,
        ApiTests.class
})

public class SmokeSuite {
}
