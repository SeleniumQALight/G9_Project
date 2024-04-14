package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    public HomePage(WebDriver webDriver) {
        super(webDriver);

    }


    @Override
    protected String getRelativeUrl() {
        return "/";
    }


    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        Assert.assertTrue("Invalid Page Not Home Page", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (this.getHeaderElement().isButtonSignOutDisplayed()) {
            logger.info("User is already logged in.");
        } else {
            loginPage.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
            loginPage.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
            loginPage.clickOnButtonSignIn();
            checkIsRedirectToHomePage();
            logger.info("User is logged in.");
        }
        return this;
    }

    //створити метод для відкриття нової вкладки



    public HomePage switchToNewTab() {
        try {
            String oldTab = webDriver.getWindowHandle();
            ((JavascriptExecutor) webDriver).executeScript("window.open();");
            for (String tab : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(tab);
            }

            logger.info("Switch to new tab");
        } catch (Exception e) {
            logger.error("Can not switch to new tab " + e);
            Assert.fail("Can not switch to new tab " + e);
        }
        return this;
    }

    public HomePage switchToMainTab() {
        try {
            String currentTab = webDriver.getWindowHandle();
            webDriver.switchTo().window(currentTab);
            logger.info("Switch to main tab");
        } catch (Exception e) {
            logger.error("Can not switch to main tab " + e);
            Assert.fail("Can not switch to main tab " + e);
        }
        return this;
    }

    public HomePage closeNewTabAndSwitchToMainTab() {
        try {
            String currentTab = webDriver.getWindowHandle();
            for (String tab : webDriver.getWindowHandles()) {
                if (!tab.equals(currentTab)) {
                    webDriver.switchTo().window(tab);
                    webDriver.close();
                    webDriver.switchTo().window(currentTab);
                }
            }
            logger.info("Close new tab and switch to main tab");
        } catch (Exception e) {
            logger.error("Can not close new tab and switch to main tab " + e);
            Assert.fail("Can not close new tab and switch to main tab " + e);
        }
        return this;
    }


    }












