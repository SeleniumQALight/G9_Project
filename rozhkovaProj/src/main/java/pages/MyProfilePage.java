package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement postSuccessfullyDeletedMessage;

    private String postTitleLocator = ".//*[text()='%s']"; //%S - підставляємо назву з тесту, яку сгенерувалося

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*"; // * тому що може бути багато символів
    }

    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check some element that must be present at this page
        return this;
    }

    //get post with title/ Приклад параметризованого локатору. Щоб зрозуміти, що пост єдиний. метод повертає список всіх елементів, які мають вказаний локаційний текст
    @Step
    private List<WebElement> getPostsWithTitle(String title) {
        String locator = String.format(postTitleLocator, title);
        return webDriver.findElements(By.xpath(locator)); //якщо елемент не знайдеться, то поверне пустий список і не буде ексепшена. це ізза findElements, закінчення s
    }

    @Step
    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle, expectedNumberOfPosts, getPostsWithTitle(postTitle).size());
        return this;
    }

    @Step
    public MyProfilePage deletePosTillPresent(String postTitle) { //видаляємо пост, поки він не зникне. знайти всі пости з нашим тайтлом і видалити
        //може while зациклюватися, якщо пост не видаляється, то треба другу умову на максимальну кількість постів
        List<WebElement> postsList = getPostsWithTitle(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;// або замість '100' можна поставити 'postList.size()'
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) { //поки список не пустий і каунтер менший за максимальну кількість постів
            clickOnElement(postsList.get(0));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePostPresent();
            logger.info("Post with title '" + postTitle + "' was deleted");
            postsList = getPostsWithTitle(postTitle);  //оновлюємо список постів
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than " + MAX_POST_COUNT + " posts with title " + postTitle + " or delete is not working");
        }
        return this;
    }

    @Step
    public MyProfilePage checkIsMessageSuccessDeletePostPresent() {
        Assert.assertTrue("Message 'Post successfully deleted.' is not displayed",
                isElementDisplayed(postSuccessfullyDeletedMessage, "Message 'Post successfully deleted.'"));
        return this;
    }
}
