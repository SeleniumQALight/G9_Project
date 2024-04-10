package postTests;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import pages.libs.Util;

public class CreateNewPost extends BaseTest {

    final String POST_TITLE = "TC_001_buturlym" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "Text of the post";
    final String SUCCESS_MESSAGE = "New post successfully created.";
    final String NOTE_MESSAGE = "Note: This post was written for One Person";
    @Test
    public void TC_001_createNewPost() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage()
            .getHeaderElement()
            .clickOnButtonCreatePost()
            .checkIsRedirectOnCreatePostPage()
            .enterTitleInToInputTitle(POST_TITLE)
            .enterTextInToInputBody(POST_BODY)
            .isCheckboxUniquePostChecked("checked")
//            .selectTextInDropdownRoleByVisibleText("Приватне повідомлення")
            .selectValueInDropdownRole("One Person")
            .clickOnSaveNewPostButton()
            .checkIsRedirectOnPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextInSuccessMessage(SUCCESS_MESSAGE)
            .checkIsPostUnique()
            .checkTextInTitle(POST_TITLE)
            .checkTextInBody(POST_BODY)
            .checkNoteMessage(NOTE_MESSAGE)
    ;

    pageProvider.getPostPage()
            .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()
            .checkPostWithTitleIsPresent(POST_TITLE, 1)
    ;
    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }

}

