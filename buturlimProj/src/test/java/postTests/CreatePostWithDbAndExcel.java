package postTests;


import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.libs.ConfigProvider;
import pages.libs.ExcelSpreadsheetData;
import pages.libs.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreatePostWithDbAndExcel extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    String postTitle;

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void createPostWithDbAndExcel(String title, String body, String status, String checkboxState, String expectedMessage, boolean isUniquePost) throws SQLException, ClassNotFoundException {
        postTitle = String.format(title, Util.getDateAndTimeFormatted(), "TC_title_Buturlym");
        body = String.format(body, Util.getDateAndTimeFormatted(), "TC_body_Buturlym");
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredNew()
                .checkIsRedirectOnHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToInputTitle(postTitle)
                .enterTextInToInputBody(body)
                .selectValueInDropdownRole(status)
                .isCheckboxUniquePostChecked(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectOnPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkIsPostUnique(isUniquePost)
                .checkTextInTitle(postTitle)
                .checkTextInBody(body);

        pageProvider.getPostPage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitle, 1);
}

    @After
    public void deletePost() throws SQLException, ClassNotFoundException {
        logger.info("AFTER");
        logger.info("Title: " + postTitle);
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeededWithDb()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle);
    }


    public Collection parametersForCreatePost() throws IOException {
        final String pathToExcelFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToExcelFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToExcelFile), sheetName, skipFirstRow).getData();
    }


}
