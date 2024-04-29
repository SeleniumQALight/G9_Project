package libs;

import org.junit.Assert;

public class StringUtils {
    public static String deleteSomeSymbols(String line, int numberOfSymbols) {
        if (line.length() <= numberOfSymbols) {
           Assert.fail("The number of characters to delete is greater than the length of the string");
        }
        return line.substring(0, line.length() - numberOfSymbols);
    }
}
