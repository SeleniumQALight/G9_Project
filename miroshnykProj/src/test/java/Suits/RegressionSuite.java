package Suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class
})
public class RegressionSuite {



}
