package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.libs.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {

    String environment = System.getProperty("env", "aqa");
   // String baseUrl = String.format("https://%s-complexapp.onrender.com", environment);

    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    String privatUrl = ConfigProvider.configProperties.privatUrl();

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page URL", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    protected void checkUrlForPrivatBank() {
        Assert.assertEquals("Invalid page URL", privatUrl, webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" + "Expected URL: " + baseUrl+getRelativeUrl() + "\n Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }


    @Step
    public void openNewTab() {
        webDriverWait15.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        logger.info("New tab was opened");
    }

    @Step
    public void switchToNewTab() {
        String oldTab = webDriver.getWindowHandle();
        for (String newTab : webDriver.getWindowHandles()) {
            if (!newTab.equals(oldTab)) {
                webDriver.switchTo().window(newTab);
                logger.info("Switched to new tab");
            }
        }
    }

    @Step
    public void switchToOldTab() {
        String oldTab = webDriver.getWindowHandle();
                webDriver.switchTo().window(oldTab);
                logger.info("Switched to old tab");
        }

    @Step
    public void closeAllTabsAndSwitchToMainTab() {
        String oldTab = webDriver.getWindowHandle();
        for (String newTab : webDriver.getWindowHandles()) {
            if (!newTab.equals(oldTab)) {
                webDriver.switchTo().window(newTab).close();
                webDriver.switchTo().window(oldTab);
                logger.info("New tab was closed and switched to main tab");
            }
        }
    }

    @Step
    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }
}