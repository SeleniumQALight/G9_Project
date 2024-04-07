package pages;


import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage checkIsRedirectOnHomePage() {
        checkUrl();
        Assert.assertTrue("Invalid page not Home page", getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("My profile img is not displayed", getHeaderElement().isMyProfileButtonDisplayed());
        Assert.assertTrue("Create Post button is not displayed", getHeaderElement().isButtonCreatePostDisplayed());
        Assert.assertTrue("Search icon is not displayed", getHeaderElement().isSearchIconDisplayed());
        Assert.assertTrue("Chat icon is not displayed", getHeaderElement().isChatIconDisplayed());
        Assert.assertTrue("Button Sign Out is not displayed", getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In displayed", getLoginPage().isButtonSignInNotDisplayed());
        Assert.assertTrue("Login Input displayed", getLoginPage().isInputLoginNotDisplayed());
        Assert.assertTrue("Password Input displayed", getLoginPage().isInputPasswordNotDisplayed());
        return this;
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()) {
            logger.info("User is already logged in");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectOnHomePage();
            logger.info("User was logged in");
        }
        return this;
    }

}
