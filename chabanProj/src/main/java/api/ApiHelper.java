package api;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public ValidatableResponse getAllPostsByUserRequest(String userName, int statusCode) {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(statusCode);

    }
}
