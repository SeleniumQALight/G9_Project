package apiDemoQa.dto;

public interface EndPointsDemoQa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOKS = BASE_URL + "/BookStore/v1/Books";
    String USER = BASE_URL + "/Account/v1/User/{0}";

}
