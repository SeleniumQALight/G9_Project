package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage(){
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {return new PostPage(webDriver);
    }

    public CreatePostPage getCreatePostPage() {return new CreatePostPage(webDriver);
    }

    public MyProfilePage getMyProfilePage() {return new MyProfilePage(webDriver);
    }
    public pages.privatBank.HomePage getPrivatBankHomePage() {
        return new pages.privatBank.HomePage(webDriver);
    }
}
