package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_karhina_" + Util.getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
                //.selectTextInDropdownRoleByVisibleText("Приватне повідомлення")
                .selectValueInDropdownRole("One Person")
                .setCheckboxPostUniqueToNeededState("Checked")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkValueInMessagePostUnique()
                .checkValueInTitleOfPost(POST_TITLE)
                .checkValueInBodyOfPost("body text")
                .checkIsMessageNotificationAboutPostForOnePersonDisplayed()
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
                .deletePostsTillPresent(POST_TITLE)
                ;
    }
}

