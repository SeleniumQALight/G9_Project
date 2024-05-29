package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static data.TestData.VALID_LOGIN_API;
import static data.TestData.VALID_PASSWORD_API;

public class Hook {

    WebDriverHelper webDriverHelper;
    private ApiHelper apiHelper = new ApiHelper();

    // picocontainer
    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }


    @Before(order = 0)
    public void setup() {

    }
    @After(order = 0)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }
        @Before(value = "@deleteAllPostsForDefaultUser", order = 50)
        @After(value = "@deleteAllPostsForDefaultUser", order = 50)
        public void deletePostsForDefaultUser() {
            apiHelper.deleteAllPostsByUserTillPresent(VALID_LOGIN_API,
                    apiHelper.getToken(VALID_LOGIN_API, VALID_PASSWORD_API));
        }


}
