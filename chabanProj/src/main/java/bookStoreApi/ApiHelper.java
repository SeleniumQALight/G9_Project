package bookStoreApi;

import bookStoreApi.dto.responceDto.BooksDto;
import bookStoreApi.dto.responceDto.GeneralInfo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    public String getTokenAndUserId(String userName, String password) {
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
                .extract().response().getBody().asString();
    }

    public void deleteBooksInProfile(String token, String userId) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("userId", userId);

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPoints.DELETE_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public ValidatableResponse getAllBooksRequest() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.BOOKS)
                .then()
                .spec(responseSpecification);

    }

    public GeneralInfo getAllBooks() {
        return getAllBooksRequest().extract().response().getBody().as(GeneralInfo.class);
    }

    public String getIsbn() {
        BooksDto[] books = getAllBooks().getBooks();
        for (int i = 0; i < books.length; i++) {
        }
        System.out.println(books[0].getIsbn());
        return books[0].getIsbn();
    }

    public void addBookToProfile(String token, String userId) {

        Map<String, Object> bodyRequest = new HashMap<>();
        bodyRequest.put("userId", userId);

        ArrayList<Map<String, String>> collectionOfIsbns = new ArrayList<>();
        Map<String, String> isbnMap = new HashMap<>();
        isbnMap.put("isbn", getIsbn());
        collectionOfIsbns.add(isbnMap);

        bodyRequest.put("collectionOfIsbns", collectionOfIsbns);


        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .when()
                .post(EndPoints.ADD_BOOK)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }

    public ValidatableResponse getBooksInProfileRequest(String token, String userId) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.BOOKS_IN_PROFILE, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK));
    }

    private GeneralInfo getBooksInProfile(String token, String userId) {
        return getBooksInProfileRequest(token, userId)
                .extract().response().getBody().as(GeneralInfo.class);
    }


           public BooksDto[] getBooks (String token, String userId) {
               GeneralInfo userProfile = getBooksInProfile(token, userId);
               BooksDto[] booksList = userProfile.getBooks();
               return booksList;
           }
}
