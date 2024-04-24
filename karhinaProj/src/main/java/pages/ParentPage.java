package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

 abstract public class ParentPage extends CommonActionsWithElements_new {
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    String env = System.getProperty("env", "aqa");
    //String baseUrl = String.format("https://%s-complexapp.onrender.com", env);

     String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env);

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
     ////https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
     //// regex for 64d21e84903640003414c338
     //// [a-zA-Z0-9]{24}
     ////https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

     protected void checkUrlWithPattern() {
         Assert.assertTrue("Invalid page \n" + "Expected url: " + baseUrl + getRelativeUrl()
                    + "\n Actual url: " + webDriver.getCurrentUrl()
                 , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
     }
}
