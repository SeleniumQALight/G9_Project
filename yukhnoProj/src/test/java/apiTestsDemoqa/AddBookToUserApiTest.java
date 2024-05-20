package apiTestsDemoqa;

import apiDemoqa.ApiHelperDemoqa;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        String isbn = apiHelperDemoqa.getAllBooks().get(0).get("isbn");
        apiHelperDemoqa.addBookToUser(userId, token, isbn);
        List<String> userBooks = apiHelperDemoqa.getUserInfo(userId, token);
        assertEquals(1, userBooks.size());
        assertTrue(userBooks.contains(isbn));

    }
}
