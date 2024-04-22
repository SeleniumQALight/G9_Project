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
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@RunWith(JUnitParamsRunner.class)
public class CreatePostDataBaseAndExcelTest extends BaseTest {
    Logger logger = Logger.getLogger(getClass());
    String title;

    @Test
    @Parameters(method = "parametersForCreatingPost")
    public void TC_002_createNewPost(String postTitle, String body, String dropdown, String state, String expectedMessage, String expectedState) throws Exception {
        SeleniumUsers seleniumUsers = new SeleniumUsers();
        final String POST_TITlE= postTitle + Util.getDateAndTimeFormatted();
        final String BODY_TEXT = body + Util.getDateAndTimeFormatted();




        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("newqaauto");
        pageProvider.getLoginPage().enterTextIntoInputPassword(seleniumUsers.getPassForLogin("newqaauto"));
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonCreatePost();
        pageProvider.getCreatePostPage().checkIsRedirectToCreatePostPage();
        pageProvider.getCreatePostPage().enterTitleInToInputTitle(POST_TITlE);
        pageProvider.getCreatePostPage().enterTextIntoInputBody(BODY_TEXT);
        pageProvider.getCreatePostPage().selectValueInDropdownRole(dropdown);
        pageProvider.getCreatePostPage().settingCheckBox(state);
        pageProvider.getCreatePostPage().clickOnSaveNewPostButton();
        pageProvider.getPostPage().checkIsRedirectToPostPage();
        pageProvider.getPostPage().checkTextInSuccessMessage(expectedMessage);
        pageProvider.getPostPage().checkIsPostUnique(expectedState);
        title =POST_TITlE;


    }
public Collection parametersForCreatingPost() throws IOException {
    final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
    final String sheetName = "createPostWithExcelCustom";
    final boolean skipFirstRow = false;
    logger.info("Data file path: " + pathToDataFile + " \nSheet name: " + sheetName + " \nSkip first row: " + skipFirstRow);
    return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
}

    @After
    public void deletePost() throws SQLException, IOException, ClassNotFoundException {
        pageProvider.getHomePage().openHomePageAndLoginIfNeeded();
        pageProvider.getHomePage().getHeaderElement().clickOnButtonMyProfile();
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
        pageProvider.getMyProfilePage().deletePostsTillPresent(title);
        }
    }



