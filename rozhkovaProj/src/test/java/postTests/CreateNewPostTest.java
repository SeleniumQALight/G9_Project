package postTests;

import baseTests.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    final String POST_TITLE = "TC_001_rozhkova_" + Util.getDateAndTimeFormatted();//# тесткейса гарантує унікальність назви поста, якщо одночасно запустити кілька тестів

    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextInToInputBody("body text")
//                .selectTextInDropDownRoleByVisibleText("Приватне повідомлення")
                .selectCheckboxPostUniqueToNeededState("cHecK")
                .selectValueInDropDownRole("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
//                .checkIsMessagePostUniqueDisplayed()
                .checkValueInPostUniqueMessage()
                .checkValueInTitleOfPost(POST_TITLE)
                .checkValueInBodyOfPost("body text")
                .checkIsMessageNotificationAboutOnePersonRoleDisplayed()
        ;


        pageProvider.getPostPage().getHeaderElement().clickOnMyProfileButton()
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
                .deletePosTillPresent(POST_TITLE)  //видаляй поки пости є

                ;
    }
}
