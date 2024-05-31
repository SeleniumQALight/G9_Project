package apiDemoqa;

import data.TestDataDemoqa;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoqa {

    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    /**
     * GET TOKEN FOR DEFAULT USER
     * @return
     */
    public Map<String, String> getTokenAndUserId() {
        return getTokenAndUserId(TestDataDemoqa.VALID_USERNAME_DEMOQA_API, TestDataDemoqa.VALID_PASSWORD_DEMOQA_API);

    }

    public Map<String, String> getTokenAndUserId(String username, String password){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", username);
        requestBody.put("password", password);

        Response response = given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoqa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("token");
        String userId = jsonPath.getString("userId");

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", userId);

        return result;

    }

    public void deleteAllBooksInProfile(String userId, String token) {
        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(EndPointsDemoqa.BOOKS)
                .then()
                .log().all()
                .statusCode(204);
    }

    public List<Map<String, String>> getAllBooks(){
        Response response =
            given()
                    .spec(requestSpecification)
                    .when()
                    .get(EndPointsDemoqa.BOOKS)
                    .then()
                    .spec(responseSpecification)
                    .extract().response();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("books");

    }

    public void addBookToUser(String userId, String token, String isbn){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);

        JSONArray collectionOfIsbns = new JSONArray();
        JSONObject isbnObj = new JSONObject();
        isbnObj.put("isbn", isbn);
        collectionOfIsbns.put(isbnObj);

        requestBody.put("collectionOfIsbns", collectionOfIsbns);

        given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoqa.BOOKS)
                .then()
                .log().all()
                .statusCode(201);

    }

    public List<String> getUserInfo(String userId, String token) {
        Response response = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPointsDemoqa.GET_USER_INFO, userId)
                .then()
                .spec(responseSpecification)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("books.isbn");



    }
}
