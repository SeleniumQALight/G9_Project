package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = ".//*[text()='%s']"; // параметризований локатор

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO check current URL
        // TODO check some element that is only on this page
        return this;
    }

    // get post with title
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        return webDriver.findElements(By.xpath(locator)); // якщо не знайде жодного, то поверне пустий список
                                                          // і не буде ексепшна
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts){
        Assert.assertEquals("Number of posts with title " + postTitle
                , expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }

}
