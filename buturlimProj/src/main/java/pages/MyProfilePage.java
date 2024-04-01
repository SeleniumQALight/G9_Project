package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{
    private String postTitleLocator = ".//*[text(),'%s']"; // параметризований локатор



    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage () {
        //TODO check url
        //TODO check some element that is only on this page
        return this;
    }

    // get post with title
    private List<WebElement> getPostsWithTitle(String post_title) {
        String locator = String.format(postTitleLocator, post_title);
        return webDriver.findElements(By.xpath(locator));    // якщо не знайдено елементи, то повертається пустий список без помилки
    }

    public MyProfilePage checkPostWithTitleIsPresent(String post_title, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + post_title, expectedNumberOfPosts, getPostsWithTitle(post_title).size());
        return this;
    }

}

