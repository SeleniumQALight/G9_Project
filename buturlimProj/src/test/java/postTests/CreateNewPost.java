package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreateNewPost extends BaseTest {

    final String POST_TITLE = "TC_001_buturlym" + Util.getDateAndTimeFormatted();
    @Test
    public void TC_001_createNewPost() {
    pageProvider.getLoginPage()
            .openLoginPageAndFillLoginFormWithValidCred()
            .checkIsRedirectOnHomePage()
            .clickOnButtonCreatePost()
            .checkIsRedirectOnCreatePostPage()
            .enterTitleInToInputTitle(POST_TITLE)
            .enterTextInToInputBody("Text of the post")
//            .selectTextInDropdownRoleByVisibleText("Приватне повідомлення")
            .selectValueInDropdownRole("One Person")
            .clickOnSaveNewPostButton()
            .checkIsRedirectOnPostPage()
            .checkIsSuccessMessageDisplayed()
            .checkTextInSuccessMessage("New post successfully created.")
    ;
    }
}

