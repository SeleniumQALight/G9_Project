import io.qameta.allure.junit4.AllureJunit4;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import suits.SmokeSuite;

public class RunTest {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Number of args " + args.length);
        if (args.length > 0) {
            Class classtest = Class.forName(args[0]);
            JUnitCore engine = new JUnitCore();
            engine.addListener(new AllureJunit4());
            engine.run(new ParallelComputer(true, true), classtest);

//            Class<?>[] classes = { classtest };

            // ParallelComputer(true,true) will run all classes and methods
            // in parallel.  (First arg for classes, second arg for methods)
//            engine.runClasses(new ParallelComputer(true, true), classes);
        } else {
            System.out.println("You should provide class with suite as parameter");
        }
    }
}
