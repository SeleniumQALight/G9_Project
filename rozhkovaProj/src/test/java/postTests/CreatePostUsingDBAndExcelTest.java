package postTests;

import baseTests.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import libs.Util;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)

public class CreatePostUsingDBAndExcelTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass()) ;
    String postTitle;

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void TC_001_createPostUsingDBAndExcel(String title, String body, String role, String checkBoxState, String expectedMessageNewPost, String textInPostUnique) throws SQLException, ClassNotFoundException {
        postTitle = String.format(title, Util.getDateAndTimeFormatted(), "Post Title Rozhkova");
        body = String.format(body, Util.getDateAndTimeFormatted());
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithNewValidCredFromDB()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(postTitle)
                .enterTextInToInputBody(body)
                .selectCheckboxPostUniqueToNeededState(checkBoxState)
                .selectValueInDropDownRole(role)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessageNewPost)
                .checkValueInPostUniqueMessage(textInPostUnique)
                .checkValueInTitleOfPost(postTitle)
                .checkValueInBodyOfPost(body)
                .checkIsMessageNotificationAboutRoleDisplayed(role)
        ;

        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1)
        ;
    }

    public Collection parametersForCreatePost() throws IOException {
        final String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
        };

    @After
    public void deletePost() {
        logger.info("block After");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePosTillPresent(postTitle)
        ;
    }
}
