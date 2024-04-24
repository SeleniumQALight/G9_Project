package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;

@RunWith(Suite.class) //файл, в якому перераховані ВСІ класи, які будемо ранити
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class
})

public class RegressionSuite {

}

