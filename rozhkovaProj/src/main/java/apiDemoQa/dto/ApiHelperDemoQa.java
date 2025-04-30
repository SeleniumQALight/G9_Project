package apiDemoQa.dto;

import apiDemoQa.dto.responseDto.BooksDto;
import apiDemoQa.dto.responseDto.UserInfoDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQa {
    Logger logger = Logger.getLogger(getClass());

    public RequestSpecification requestSpec = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();
    public ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public String getTokenAndUserId(String login, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", login);
        requestBody.put("password", password);

        return given()
                .spec(requestSpec)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then()
                .spec(responseSpec)
                .extract().response().asString();
    }

    public void deleteAllUserBooks(String token, String userId) {
        HashMap<String, String> requestBody = new HashMap<>();
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParams("UserId", userId)
                .when()
                .delete(EndPointsDemoQa.BOOKS)
                .then()
                .spec(responseSpec.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public ValidatableResponse getBooksByUserRequest(String token, String userId) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoQa.USER, userId)
                .then()
                .spec(responseSpec.statusCode(HttpStatus.SC_OK));
    }

    public UserInfoDto getBooksByUser(String token, String userId) {
        return getBooksByUserRequest(token, userId)
                .extract().response().getBody().as(UserInfoDto.class);
    }

    public BooksDto[] getBooks(String token, String userId) {
        UserInfoDto userInfoDto = getBooksByUser(token, userId);
        BooksDto[] books = userInfoDto.getBooks();
        return books;
}

    public ValidatableResponse getAllBooksRequest() {
        return given()
                .spec(requestSpec)
                .when()
                .get(EndPointsDemoQa.BOOKS)
                .then()
                .spec(responseSpec.statusCode(HttpStatus.SC_OK))
                .log().all();
    }
public UserInfoDto getAllBooks() {
    return getAllBooksRequest()
            .extract().response().getBody().as(UserInfoDto.class);
}

    public String getISBN(int bookNumber){
        BooksDto[] books = getAllBooks().getBooks();
        return books[bookNumber].getIsbn();
    }

    public void addBookToUser(String token, String userId){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        ArrayList<HashMap<String, String>> collectionOfIsbn = new ArrayList<>();
        HashMap<String, String> isbn = new HashMap<>();
        isbn.put("isbn", getISBN(0));
        collectionOfIsbn.add(isbn);
        requestBody.put("collectionOfIsbns", collectionOfIsbn);
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoQa.BOOKS)
                .then()
                .spec(responseSpec.statusCode(HttpStatus.SC_CREATED));
    }
}
