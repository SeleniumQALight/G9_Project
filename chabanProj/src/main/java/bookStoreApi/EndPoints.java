package bookStoreApi;

public interface EndPoints {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOKS_IN_PROFILE = BASE_URL + "/Account/v1/User/{UUID}";
    String BOOKS = BASE_URL + "/BookStore/v1/Books";

}
