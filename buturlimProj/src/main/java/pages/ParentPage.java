package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.libs.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {

    String environment = System.getProperty("env", "aqa");
   // String baseUrl = String.format("https://%s-complexapp.onrender.com", environment);

    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page URL", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" + "Expected URL: " + baseUrl+getRelativeUrl() + "\n Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}