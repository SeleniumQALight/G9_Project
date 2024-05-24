package api;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";
    String LOGIN = BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String DELETE_POST = BASE_URL + "/api/post/{0}";
    String DEMO_QA_BASE_URL = "https://demoqa.com";
    String DEMO_QA_BOOKS = DEMO_QA_BASE_URL + "/BookStore/v1/Books";
    String DEMO_QA_USER = DEMO_QA_BASE_URL + "/Account/v1/User/{0}";
    String DEMO_QA_LOGIN = DEMO_QA_BASE_URL + "/Account/v1/Login";
}
