package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements {

    String baseUrl = "https://aqa-complexapp.onrender.com";

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