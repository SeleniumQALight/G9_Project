package apiTests.demoQATest;

import demoQaApi.dto.ApiHelper;
import demoQaApi.dto.responseDto.BooksDto;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.*;

public class DemoQaTest {
    String token;
    String userId;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void getTokenAndUserIdAndDeleteBooks() {
        String response = apiHelper.getTokenAndUserId(DEMO_QA_VALID_LOGIN, DEMO_QA_VALID_PASSWORD);
        JSONObject responseJson = new JSONObject(response);
        token = responseJson.optString("token");
        userId = responseJson.optString("userId");
        apiHelper.deleteBooksFromUser(token, userId);
    }

    @Test
    public void getAndAddBooksToUser(){
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
