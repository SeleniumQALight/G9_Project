package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.libs.ConfigProvider;
import pages.libs.ExcelSpreadsheetData;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesWithExcelTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    @Test
    @Parameters(method = "parametersForValidationMessagesTest")
    public void validationMessagesTest (String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUsernameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessagesTexts(expectedMessages);
    }

    public Collection parametersForValidationMessagesTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile  + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}
