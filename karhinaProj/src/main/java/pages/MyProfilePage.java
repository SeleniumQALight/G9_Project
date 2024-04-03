package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private String postTitleLocator = ".//*[text()='%s']"; //приклад параметризованого локатору

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
            private WebElement postSuccessfullyDeleteMessage;



    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check some element that is only on this page
        return this;
    }

    //get post with title
    private List <WebElement>  getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        return webDriver.findElements(By.xpath(locator)); // якщо не  знайде жодного елементу то поверне пустий список
                                                            // і не буде ексепшена
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }


    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsWithTitle(postTitle);
        int counter = 0;
        final int Max_Post_Count = 100; //postsList.size();
        while (!postsList.isEmpty() && counter < Max_Post_Count) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessfullyDeletePresent();
            logger.info("Post with title " + postTitle + " was deleted.");
            postsList = getPostsWithTitle(postTitle);
            counter++;
        }
        if (counter >= Max_Post_Count) {
            Assert.fail("There are more than " + Max_Post_Count + " post with title " + postTitle);
            logger.info("Number of posts with title " + postTitle + " more than " + Max_Post_Count);
        }
        return this;
    }
public MyProfilePage checkIsMessageSuccessfullyDeletePresent() {
        Assert.assertTrue("Post successfully deleted message is not displayed",
                isElementDisplayed(postSuccessfullyDeleteMessage, "Post successfully deleted message"));

        return this;
    }

}

