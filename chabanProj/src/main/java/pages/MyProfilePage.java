package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//*[text()='Post successfully deleted.']")
    private WebElement postSuccessfullyDeletedMessage;

    private String postTitleLocator = ".//*[text()='%s']";

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }
    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check some element that is only on this page
        return this;
    }
    @Step
    //get post with title
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        return webDriver.findElements(By.xpath(locator)); //якщо не знайде елемент, то поверне пустий список і не буде єксепшена
    }
    @Step
    public MyProfilePage checkPostWithTitleIsPresented(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle
                ,expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }
    @Step
    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsWithTitle(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100; //post.List.size()
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeleteDisplayed();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle);
        }
        return this;
    }
    @Step
    public MyProfilePage checkIsMessageSuccessDeleteDisplayed() {
        Assert.assertTrue("Success message is not displayed"
                , isElementDisplayed(postSuccessfullyDeletedMessage, "Success message"));
        return this;
    }

    public MyProfilePage checkNumberOfPostsInPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts is not expected", numberOfPosts, postList.size());

        return this;
    }
}
