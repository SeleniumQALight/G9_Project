package apiTest.BookStore;

import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.BookStoreTestData.*;


public class AddBookToProfileTestApi {
        String token;
        String userId;
        bookStoreApi.ApiHelper apiHelper = new  bookStoreApi.ApiHelper();

        @Before
        public void getTokenUserIdAndDeleteBooks(){
            String response= apiHelper.getTokenAndUserId(VALID_LOGIN_API, VALID_PASSWORD_API);
            JSONObject responseJson = new JSONObject(response);
            token = responseJson.optString("token");
            userId = responseJson.optString("userId");
        apiHelper.deleteBooksInProfile(token, userId);


        }
        @Test
    public void addBookToProfile(){

                int numberOfBooksBeforeAdding = apiHelper.getBooks(token,userId).length;
                apiHelper.addBookToProfile(token, userId);
                int numberOfBooksAfterAdding = apiHelper.getBooks(token,userId).length;
                Assert.assertEquals("Number of books didn't change", numberOfBooksBeforeAdding+1, numberOfBooksAfterAdding);

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(apiHelper.getBooks(token, userId)[numberOfBooksAfterAdding-1].getIsbn())
                   .as("ISBN of added book")
                   .isEqualTo(apiHelper.getIsbn(0));

            softAssertions.assertAll();
        }

}
