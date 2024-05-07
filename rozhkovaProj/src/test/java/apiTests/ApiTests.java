package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";
    @Test

    public void getAllPostsForUser() {
        given()//починаємо готувати запит, що отримати пости для користувача: авторизацію, налаштовувати хедери, якщо є
                .contentType(ContentType.JSON)//вказуємо, що хочемо отримати дані у форматі JSON
                .log().all()//виводимо в консоль всі дані, які відправляються на сервер

                .when()//вказуємо, що саме хочемо зробити - дія
                .get(EndPoints.POSTS_BY_USER, USER_NAME)//вказуємо метод запиту та шлях до ресурсу

                .then()//після виконання запиту перевіряємо результат
                .log().all()//виводимо в консоль всі дані, які отримали від сервера
                .statusCode(200);//перевіряємо, що статус код відповіді 200

    }
}
