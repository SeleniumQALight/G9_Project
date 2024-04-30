package postTests;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    private final String POST_TITLE = "TC_001_lapchak " + Util.getDateAndTimeFormatted();

    @Test
    public void TC_001_createNewPost() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderElement().clickOnButtonCreatePost()
                .checkIsRedirectedToCreatePostPage()
                .enterTitleInToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("body text")
                .selectValueInDropdownRole("One Person")
                .checkBoxState("unchecked")
                .clickOnSaveNewPostButton()
                .checkIsRedirectedToPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkIsPostUnique()
                .checkIsPostTitle(POST_TITLE)
                .checkPostBody("body text")
                .checkPostNote();

        pageProvider.getPostPage()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .checkIsPostWithTitleIsPresent(POST_TITLE, 1)
        ;


    }

    @After
    public void deletePost() {
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderElement().clickOnButtonMyProfile()
                .checkIsRedirectedToMyProfilePage()
                .deletePostsTillPresent(POST_TITLE);
    }

}
