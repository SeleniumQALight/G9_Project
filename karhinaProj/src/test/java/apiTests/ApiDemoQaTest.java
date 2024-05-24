package apiTests;

import apiDemoQa.dto.ApiHelperDemoQa;
import data.TestData;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApiDemoQaTest {
    String token;
    String userId;
    ApiHelperDemoQa apiHelper = new ApiHelperDemoQa();

    @Before
    public void getTokenAndDeleteAllBooks() {
        String response = apiHelper.getTokenAndUserIdDemoQa(TestData.VALID_LOGIN_API_DEMO_QA, TestData.VALID_PASSWORD_API_DEMO_QA);
        JSONObject responseJson = new JSONObject(response);
        token = responseJson.optString("token");
        userId = responseJson.optString("userId");
        apiHelper.deleteAllUsersBooks(token, userId);
    }

    @Test

    public void addBookByApi() {
        int numberOfBooksBeforeAdding = apiHelper.getBooks(token, userId).length;
        apiHelper.addBookToUser(token, userId);

        int numberOfBooksAfterAdding = apiHelper.getBooks(token, userId).length;

        Assert.assertEquals("Number of books ", numberOfBooksBeforeAdding + 1, numberOfBooksAfterAdding);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getBooks(token, userId)[numberOfBooksAfterAdding - 1].getIsbn())
                .as("ISBN in response")
                .isEqualTo(apiHelper.getISBN(0));
        softAssertions.assertAll();

    }

}
