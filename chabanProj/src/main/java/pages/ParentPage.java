package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionWithElements {

    String env = System.getProperty("env", "aqa");
   // String baseUrl = String.format("https://%s-complexapp.onrender.com", env);
    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid Page",
                baseUrl + getRelativeUrl() //метод перевіряє чи поточний URL відповідає очікуваному
                , webDriver.getCurrentUrl());
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
//https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24}
//https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid Page /n"
                        + "Expected URL : " + baseUrl + getRelativeUrl() +
                        "/n" + "Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
