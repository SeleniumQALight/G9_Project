package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements {

    String env = System.getProperty("env", "aqa");
    String baseUrl = String.format("https://%s-complexapp.onrender.com", env);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();
    protected void checkUrl() {
        Assert.assertEquals("Invalid page",
                baseUrl + getRelativeUrl() , //expected
                webDriver.getCurrentUrl());  //actual
    }

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                        + "Expected url: " + baseUrl + getRelativeUrl()
                        + "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
