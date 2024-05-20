package postTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)

public class CreatePostWithExcelAndDB extends BaseTest {

    Logger logger = Logger.getLogger(getClass());
    String postTitle;

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void createPostWithExcelAndDB(String title, String body, String role, String checkboxState,
                                         String expectedMessage, String textInPostUnique) throws SQLException, ClassNotFoundException, IOException {

        final String postTitleText = String.format(title, Util.getDateAndTimeFormatted(), "Title Horobets");
        final String bodyText = String.format(body, Util.getDateAndTimeFormatted(), "Body Horobets");
        postTitle = postTitleText;

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCredFromDB()
                .chekIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(postTitleText)
                .enterTextIntoInputBody(bodyText)
                .selectValueInDropdownRole(role)
                .setCheckboxStateCheckOrUncheck(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkMessageIsPostUnique()
                .checkValueInMessagePostUnique(textInPostUnique)
                .checkTextTitleOfPost(postTitleText)
                .checkTextBodyOfPost(bodyText)

        ;

        pageProvider.getPostPage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(postTitleText, 1)
        ;
    }

    public Collection parametersForCreatePost() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "createPostWithExcel";
        final boolean skipFirstRow = false;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }

    @After
    public void deletePosts() {
        logger.info("After");
        logger.info("Post title: " + postTitle);
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitle)
        ;

    }
}

