package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class ApiSteps extends MainSteps {
    final private String DEFAULT = "default";
    private ApiHelper apiHelper = new ApiHelper();
    public ApiSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I create {} new posts via API for {string} user and {string} password")// {} - це параметр, який може бути будь-яким числом. якщо треба інший ти, то треба явно прописати
    public void iCreateNewPostsViaAPIForDefaultUserAndDefaultPassword(
            int numberOfPosts, String userName, String password, DataTable dataTable) {
        //логіка змінних, а не логіки взаємодії з браузером, наприклад
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN_API;
            password = TestData.VALID_PASSWORD_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD_API;
        }
        //1. отримати токен
        String token = apiHelper.getToken(userName, password);
        //2. створити пости, кількість яку передамо
        apiHelper.createPosts(numberOfPosts, token, dataTable.asMap());
    }
}