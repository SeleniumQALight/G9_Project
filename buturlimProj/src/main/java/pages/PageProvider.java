package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class PageProvider {
   private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step
    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    @Step
    public HomePage getHomePage(){
        return new HomePage(webDriver);
    }

    @Step
    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }

    public MyProfilePage getMyProfilePage() {
        return new MyProfilePage(webDriver);
    }
}
