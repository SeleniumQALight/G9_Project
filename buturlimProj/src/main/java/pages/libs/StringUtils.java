package pages.libs;

import org.junit.Assert;

public class StringUtils {
    public static String deleteSomeSymbols(String line, int numberOfSymbols){
        if (line.length() <= numberOfSymbols){
            Assert.fail("The line is too short for deleting symbols");
        }
        return line.substring(0, line.length() - numberOfSymbols);
    }
}
