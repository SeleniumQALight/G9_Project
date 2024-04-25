package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    private String postTitleLocator = " .//*[contains(text(),'%s')]"; // параметризований локатор

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement postSuccessfullyDeletedMessage;
    @FindBy(xpath = ".//div//img[@class='avatar-small']")
    private WebElement avatarSmall;
    @FindBy(xpath = ".//div[@class='profile-nav nav nav-tabs pt-2 mb-4']")
    private WebElement profileNav;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage () {
        checkUrlWithPattern();
        Assert.assertTrue("Small avatar is not displayed", isElementDisplayed(avatarSmall, "Avatar small"));
        Assert.assertTrue("Profile navigation is not displayed", isElementDisplayed(profileNav, "Profile nav"));
        return this;
    }

    // get post with title
    private List<WebElement> getPostsWithTitle(String post_title) {
        String locator = String.format(postTitleLocator, post_title);
        return webDriver.findElements(By.xpath(locator));    // якщо не знайдено елементи, то повертається пустий список без помилки
    }

    @Step
    public MyProfilePage checkPostWithTitleIsPresent(String post_title, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + post_title, expectedNumberOfPosts, getPostsWithTitle(post_title).size());
        return this;
    }

    @Step
    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> posts = getPostsWithTitle(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!posts.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(posts.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectOnPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsPostSuccessfullyDeletedMessageDisplayed();
            logger.info("Post with title " + postTitle + " was deleted");
            posts = getPostsWithTitle(postTitle);   // оновлюємо список постів
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle);
        }
        return this;
    }

    @Step
    public MyProfilePage checkIsPostSuccessfullyDeletedMessageDisplayed() {
        Assert.assertTrue("Post successfully deleted message is not displayed", isElementDisplayed(postSuccessfullyDeletedMessage, "Post successfully deleted message"));
        return this;
    }
}

