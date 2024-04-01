package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
final String POST_TITLE = "TС_001_tsivil " + Util.getDateAndTimeFormatted();
    @Test
   public void TC_001_createNewPost(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(POST_TITLE)
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
