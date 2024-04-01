package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_chaban" + Util.getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
                .settingCheckBox("check")
                .selectValueInDropdownRole("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTitlesValue(POST_TITLE)
                .checkBodyValue("body text")
                .checkBodyNoteValue("One Person")
                .checkIsPostUnique("Is this post unique? : yes");



        pageProvider.getPostPage()
                .getHeaderElement().clickOnButtonMyProfile();
    }


}
