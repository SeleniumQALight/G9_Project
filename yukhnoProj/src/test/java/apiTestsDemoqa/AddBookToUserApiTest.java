package apiTestsDemoqa;

import apiDemoqa.ApiHelperDemoqa;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class AddBookToUserApiTest {
    ApiHelperDemoqa apiHelperDemoqa = new ApiHelperDemoqa();
    private String userId;
    private String token;

    @Before
    public void getTokenAndUserIdAndDeleteBooks(){
        Map<String, String> AuthorisationData = apiHelperDemoqa.getTokenAndUserId();
        userId = AuthorisationData.get("userId");
        token = AuthorisationData.get("token");

        apiHelperDemoqa.deleteAllBooksInProfile(userId, token);
    }

    @Test
    public void AddBookToUserApi(){
        String isbn = apiHelperDemoqa.getAllBooks();
        apiHelperDemoqa.addBookToUser(userId, token, isbn);
        apiHelperDemoqa.getUserInfo(userId, token, isbn);


    }
}
