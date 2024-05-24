package apiDemoQa.dto;

public interface EndPoints {

        String BASE_URL = "https://demoqa.com";
        String LOGIN = BASE_URL + "/Account/v1/Login";
        String DELETE_ADD_GET_ALL_BOOKS_BY_USER = BASE_URL + "/BookStore/v1/Books";
        String GET_USER_INFO = BASE_URL + "/Account/v1/User/{0}";

}
