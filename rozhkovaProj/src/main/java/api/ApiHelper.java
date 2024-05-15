package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ApiHelper {//зібрані методи, які будуть виконувати всі коли
    public ValidatableResponse getAllPostsByUserRequest(String userName, int statusCode) {//метод повертає весь респонс, який ми можемо перевірити, бо ValidatableResponse
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(statusCode);
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK); //instead of 200
    }
}
