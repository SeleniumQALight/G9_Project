package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements {

    String baseUrl = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    // метод, що перевіряє співпадіння URL
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl() // expected url
                , webDriver.getCurrentUrl()); // actual url
    }


    // метод для перевірки чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n +" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl()
                , webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

}
