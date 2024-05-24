package demoqaApi.dto;

import api.EndPoints;
import demoqaApi.dto.responseDto.BooksDto;
import demoqaApi.dto.responseDto.UserInfoDto;
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

public class ApiHelper {
    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getBooksByUserRequest(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.DEMO_QA_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(200));
    }

    public UserInfoDto getBooksByUser(String token, String userId) {
        return getBooksByUserRequest(token, userId).extract().response().getBody()
                .as(UserInfoDto.class);
    }

    public BooksDto[] getBooks(String token, String userId) {
        UserInfoDto userInfoDto = getBooksByUser(token, userId);
        BooksDto[] books = userInfoDto.getBooks();
        return books;
    }

    public String getTokenAndUserId(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.DEMO_QA_LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString();
    }

    public void deleteBooksFromUser(String token, String userId) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("userId", userId);

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPoints.DEMO_QA_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public String getISBN(int number) {
        BooksDto[] books = getAllBooks().getBooks();
        return books[number].getIsbn();
    }

    public ValidatableResponse getAllBooksRequest() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.DEMO_QA_BOOKS)
                .then()
                .spec(responseSpecification)
                .log().all();
    }

    public UserInfoDto getAllBooks() {
        return getAllBooksRequest().extract().response().getBody()
                .as(UserInfoDto.class);
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
                .post(EndPoints.DEMO_QA_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }


}
