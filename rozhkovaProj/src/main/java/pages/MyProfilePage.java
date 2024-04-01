package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{
    private String postTitleLocator = ".//*[text()='%s']"; //%S - підставляємо назву з тесту, яку сгенерувалося

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }



    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check current URL
        //TODO check some element that must be present at this page
        return this;
    }

    //get post with title/ Приклад параметризованого локатору. Щоб зрозуміти, що пост єдиний. метод повертає список всіх елементів, які мають вказаний локаційний текст
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        return webDriver.findElements(By.xpath(locator)); //якщо елемент не знайдеться, то поверне пустий список і не буде ексепшена. це ізза findElements, закінчення s
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title "+ postTitle, expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }


}
