package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements {

    String env = System.getProperty("env", "aqa"); // default value is aqa - якщо не вказано в проперті командній строці
    //   String baseUrl = String.format("https://%S-complexapp.onrender.com", env);

    // https://[env]-complexapp.onrender.com
    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env); // замість строки вище  // replace("[env]", env) - замінюємо [env] на env

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl() // expected result
                , webDriver.getCurrentUrl()); // actual result
    }

    // метод для перевірки чи відкрита потрібна сторінка по патерну
//https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24}
//https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                "\n Actual url: "+ webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }
}
