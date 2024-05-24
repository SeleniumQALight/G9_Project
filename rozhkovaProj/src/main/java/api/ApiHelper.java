package api;

import api.dto.responseDto.PostDto;
import data.TestData;
import io.qameta.allure.restassured.AllureRestAssured;
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

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiHelper {//зібрані методи, які будуть виконувати всі коли
    Logger logger = Logger.getLogger(getClass()); //getclass - повертає клас, в якому ми знаходимося (ApiHelper)

    //щоб винести повторювані запити/поля: наприклад, given
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .addFilter(new AllureRestAssured()) //додаємо фільтр, щоб відображати в аллюр репорті
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL) //=log().all(), такий синтаксис у RequestSpecBuilder
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public ValidatableResponse getAllPostsByUserRequest(String userName, int statusCode) {//метод повертає весь респонс, який ми можемо перевірити, бо ValidatableResponse
        return given()
               // .contentType(ContentType.JSON)
               // .log().all() //замінили на requestSpecification
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
               // .log().all()
               // .statusCode(statusCode) //замінили на responseSpecification і підкорегували статус код, не для ок
                .spec(responseSpecification.statusCode(statusCode));
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK); //instead of 200
    }

    /**
     * Get all posts by default user API
     * @return
     */
    public ValidatableResponse getAllPostsByUserRequest() {
        return getAllPostsByUserRequest(TestData.VALID_LOGIN_API.toLowerCase(), HttpStatus.SC_OK);
    }

    public PostDto[] getAllPostsByUserAsDto(String userName) { //повертає масив обєктів з постами
        return getAllPostsByUserRequest(userName).extract().response().getBody().as(PostDto[].class);
    }

    /**
     * GET TOKEN FOR DEFAULT USER API
     * @return
     */
    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject(); //дозволяє зберігати різні обєкти різних типів (з вкладеннями, масивами)
        requestBody.put("username", userName);//додаємо в обект ключ-значення
        requestBody.put("password", password);

        return given()// відправ запит, розбери респонс як стрінгу
                .spec(requestSpecification)
                .body(requestBody.toMap())//перетворюємо в мапу, бо restassured не працює з json
                .when()
                .post(EndPoints.LOGIN) //URL is empty, because we have baseURI in EndPoints
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().asString().replace("\"", "");//витягуємо тіло відповіді, як стрінгу і прибрати лапки в токені з екрануванням "

    }

    public void deleteAllPostsTillPresent(String validLoginApi, String token) {
        //немає арі який видаляє всі пости. Треба отримати всі пости і почерзі видаляти
        //перша частина - отримати всі пости
        PostDto[] listOfPosts = getAllPostsByUserAsDto(validLoginApi);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(listOfPosts[i].getId(), token);
            logger.info(String.format("Post with id %s and title %s was deleted", listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }

    }

    public void deletePostById(String id, String token) {
        //опишемо боді через hashmap
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

