package apiDemoqa;


public interface EndPointsDemoqa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String DELETE_ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String GET_ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String ADD_BOOK_TO_USER = BASE_URL + "/BookStore/v1/Books";
    String GET_USER_INFO = BASE_URL + "/Account/v1/User/{0}";
}
