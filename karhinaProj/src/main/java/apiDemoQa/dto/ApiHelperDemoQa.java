package apiDemoQa.dto;

import apiDemoQa.dto.responseDto.BooksDto;
import apiDemoQa.dto.responseDto.InformationAboutUserDto;
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

    public RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    Logger logger = Logger.getLogger(getClass());

    public String getTokenAndUserIdDemoQa(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString();
    }

    public void deleteAllUsersBooks(String token, String userId) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("userId", userId);

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPoints.DELETE_ADD_GET_ALL_BOOKS_BY_USER)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public ValidatableResponse getBooksByUserRequest(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_USER_INFO, userId)
                .then()
                .spec(responseSpecification.statusCode(200));
    }

    public InformationAboutUserDto getBooksByUser(String token, String userId) {
        return getBooksByUserRequest(token, userId).extract().response().getBody()
                .as(InformationAboutUserDto.class);
    }

    public BooksDto[] getBooks(String token, String userId) {
        InformationAboutUserDto informationAboutUserDto = getBooksByUser(token, userId);
        BooksDto[] books = informationAboutUserDto.getBooks();
        return books;
    }

    public String getISBN(int number) {
        BooksDto[] books = getAllBooks().getBooks();
        return books[number].getIsbn();
    }

    public ValidatableResponse getAllBooksRequest() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.DELETE_ADD_GET_ALL_BOOKS_BY_USER)
                .then()
                .spec(responseSpecification)
                .log().all();
    }

    public InformationAboutUserDto getAllBooks() {
        return getAllBooksRequest().extract().response().getBody()
                .as(InformationAboutUserDto.class);
    }

    public void addBookToUser(String token, String userId) {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("userId", userId);

        ArrayList<HashMap<String, String>> collectionOfIsbn = new ArrayList<>();
        HashMap<String, String> isbn = new HashMap<>();
        isbn.put("isbn", getISBN(0));
        collectionOfIsbn.add(isbn);
        bodyRequest.put("collectionOfIsbns", collectionOfIsbn);

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest.toMap())
                .when()
                .post(EndPoints.DELETE_ADD_GET_ALL_BOOKS_BY_USER)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }

}


