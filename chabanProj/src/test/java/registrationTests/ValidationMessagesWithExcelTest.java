package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesWithExcelTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    @Parameters(method = "parametersForValidationMessageTest")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages){
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessagesText(expectedMessages);

    }
    public Collection parametersForValidationMessageTest() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + " \nSheet name: " + sheetName + " \nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

}
