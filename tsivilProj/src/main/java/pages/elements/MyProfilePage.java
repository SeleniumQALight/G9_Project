package pages.elements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;
import pages.PostPage;

import java.util.List;

public class  MyProfilePage extends ParentPage {
    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement postSuccessfullyDeletedMessage;

    private String postTitleLocator = ".//*[text()='%s']"; // параметризированный локатор
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO checkCurrentUrl
        // TODO check some element that can be only on this page
        return this;
    }
    // get post title
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
     return webDriver.findElements(By.xpath(locator)); // якщо неи знайде жодного то поверне пустий список
        // і не буде ексепшена
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle
                , expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }


    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsWithTitle(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100; // postsList.size()
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle);
        }
        return this;
    }

    public MyProfilePage checkIsMessageSuccessDeletePresent(){
        Assert.assertTrue("Message 'Post Successfully Deleted' is not displayed"
                , isElementDisplayed(postSuccessfullyDeletedMessage, "Post Successfully Deleted message"));
        return this;
    }
}
