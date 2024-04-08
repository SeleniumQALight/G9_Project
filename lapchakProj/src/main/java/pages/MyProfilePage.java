package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = "//*[text()='Post successfully deleted.']") // локатор для успішного видалення поста
    private WebElement successPostDeletedElement;

    private String postTitleLocator = ".//*[text()='%s']"; // параметризований локатор

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectedToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check some element that is only on this page
        return this;
    }

    // get post with title
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        // TODO get post with title
        return webDriver.findElements(By.xpath(locator)); // якщо не знайде то поверне пустий список і не викине ексепшна
    }

    public MyProfilePage checkIsPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        // TODO check is post with title present
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        // TODO delete posts till present
        List<WebElement> postsList = getPostsWithTitle(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100; // postList.size() може бути замість 100
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectedToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectedToMyProfilePage()
                    .checkIsMessageSuccessDeletedPresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            logger.error("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle + " or something went wrong");
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle + " or something went wrong");
        }
        return this;
    }

    public MyProfilePage checkIsMessageSuccessDeletedPresent() {
        Assert.assertTrue("Message 'Post Successfully Deleted' is not displayed", isElementDisplayed(successPostDeletedElement, "Post Successfully Deleted"));
        return this;
    }
}
