package apiDemoqa;


public interface EndPointsDemoqa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOKS = BASE_URL + "/BookStore/v1/Books";
    String GET_USER_INFO = BASE_URL + "/Account/v1/User/{0}";
}
