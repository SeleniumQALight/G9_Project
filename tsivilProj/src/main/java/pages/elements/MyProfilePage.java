package pages.elements;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ParentPage;

import java.util.List;

public class  MyProfilePage extends ParentPage {
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


}
