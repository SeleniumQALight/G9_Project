package postTests;


import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TC_001_horobets" + Util.getDateAndTimeFormatted();

        @Test
    public void TC_001_createNewPost() {
            pageProvider.getLoginPage()
                    .openLoginPageAndFillLoginFormWithValidCred()
                    .chekIsRedirectToHomePage()
                    .clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTitleIntoInputTitle(POST_TITLE)
                    .enterTextIntoInputBody("body text")
                    //.selectTextInDropdownRoleByVisibleText("Приватне повідомлення")
                    .selectValueInDropdownRole("One Person")
                    .setCheckboxStateCheckOrUncheck("check")
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage("New post successfully created.")
                    .checkMessageIsPostUnique()
                    .checkTextInMessagePostUnique()
                    .checkTextTitleOfPost(POST_TITLE)
                    .checkTextBodyOfPost("body text")
                    .checkIsNoteDisplayed()
                    .checkTextNoteOfPostDisplayed("Note: This post was written for One Person")
            ;



        }

}
