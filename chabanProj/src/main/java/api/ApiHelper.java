package api;

import api.dto.responceDto.PostsDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
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

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {
  private   Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured())
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostsByUserRequest(String userName, int statusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(statusCode);
                .spec(responseSpecification.statusCode(statusCode));

    }
    public ValidatableResponse getAllPostsByUserRequest(String userName){
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }

    /**
     * Get all posts for default user API
     * @return
     */
    public ValidatableResponse getAllPostsByUserRequest(){
        return getAllPostsByUserRequest(TestData.VALID_LOGIN_API, HttpStatus.SC_OK);
    }
    public PostsDto[] getAllPostsByUser(String userName){
        return getAllPostsByUserRequest(userName).extract().response().getBody()
                .as(PostsDto[].class);

    }

    /**
     * Get token for default user
     * @return
     */
    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }
    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

      return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)//URL
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().asString().replace("\"","");
    }

    public void deleteAllPostsTilPresent(String validLoginApi, String token) {
        PostsDto[] listOfPosts = getAllPostsByUser(validLoginApi);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(listOfPosts[i].getId(), token);
            logger.info(String.format("Post with id %s and title '%s' was deleted",
                    listOfPosts[i].getId(), listOfPosts[i].getTitle()));

        }
    }

    private void deletePostById(String id, String token) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }
}
