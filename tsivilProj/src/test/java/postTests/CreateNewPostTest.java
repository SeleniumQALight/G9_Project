package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;


public class CreateNewPostTest extends BaseTest {
    //final String BODY_TEXT = "TС_001_tsivil " + Util.getDateAndTimeFormatted();;
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
                .checkCheckBoxNeededState("Checked")
                .selectValueInDropdownRole("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkYourTitleIsPostWasAdded(POST_TITLE)
                .checkYourBodyIsPostWasAdded("body text")
                .checkTheNoteAboutOnePersonIsPresent("One Person")



        ;


        pageProvider.getPostPage()
                .getHeaderElement().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitleIsPresent(POST_TITLE, 1)
                ;

    }
    @After
    public void deletePost(){
       pageProvider.getHomePage()
               .openHomePageAndLoginIfNeeded()
               .getHeaderElement().clickOnMyProfileButton()
               .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE)
               ;
    }
}
