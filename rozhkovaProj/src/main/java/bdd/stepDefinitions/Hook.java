package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static data.TestData.*;

public class Hook {
    WebDriverHelper webDriverHelper;
    private ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {//конструктор для того щоб picocontainer(з pom) міг передати об'єкт класу WebDriverHelper в клас Hook
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 0)
    public void setUp() {
    }

    @After (order = 0)
    public void tearDown() {
        webDriverHelper.getWebDriver().quit();
    }

    @Before(value = "@deleteAllPostsForDefaultUser", order = 50)
    @After(value = "@deleteAllPostsForDefaultUser", order = 50)
    public void deletePostsForDefaultUser() {
        //логіка видалення постів для дефолтного юзера
     apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, apiHelper.getToken(VALID_LOGIN_API, VALID_PASSWORD_API));
    }
}
