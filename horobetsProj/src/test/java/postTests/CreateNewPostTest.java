package postTests;


import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {

    final String POST_TITLE = "TC_001_horobets_" + Util.getDateAndTimeFormatted();

        @Test
    public void TC_001_createNewPost() {
            pageProvider.getLoginPage()
                    .openLoginPageAndFillLoginFormWithValidCred()
                    .chekIsRedirectToHomePage()
                    .getHeaderElement().clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTitleIntoInputTitle(POST_TITLE)
                    .enterTextIntoInputBody("body text")
                    //.selectTextInDropdownRoleByVisibleText("Приватне повідомлення")
                    .selectValueInDropdownRole("One Person")
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkIsSuccessMessageDisplayed()
                    .checkTextInSuccessMessage("New post successfully created.")
            ;





            pageProvider.getPostPage()
                    .getHeaderElement().clickOnMyProfileButton()
            ;



        }

}
