package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;

import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;


public class ApiSteps extends MainSteps {
    final private String DEFAULT = "default";
    final private ApiHelper apiHelper = new ApiHelper();

    public ApiSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNumberOfPostsNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }

        String token = apiHelper.getToken(userName, password);
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());
    }
}
