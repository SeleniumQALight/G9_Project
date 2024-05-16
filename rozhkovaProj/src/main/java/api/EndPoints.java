package api;


import java.net.URI;

public interface EndPoints { //інтерфейс тому що використовуємо фічі рест асуред
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";

    String LOGIN = BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String DELETE_POST = BASE_URL + "/api/post/{0}"; //0- це буде id поста (який треба підставити, будь яке значення), який треба видалити
}
