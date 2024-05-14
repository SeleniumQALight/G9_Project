package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responsDto.AuthorDto;
import api.dto.responsDto.PostsDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    private ApiHelper apiHelper = new ApiHelper();
    @Test
    public void getAllPostsForUser() {
        PostsDto[] actualResponseAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POSTS_BY_USER, USER_NAME)
                        .then()
                        .log().all()
                        .statusCode(200)
                        // method 1 - rest assured assertion
                        .assertThat()
                        .body("[0].title", equalTo("The second Default post"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        // method 2 - DTO
                        .extract().body().as(PostsDto[].class);
        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());
        logger.info("Username [0] = " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("UserName is not matched in post "
                    , USER_NAME, actualResponseAsDto[i].getAuthor().getUsername());

        }

        //Expected result
        PostsDto[] expectedResponseDto = {
//                new PostsDto("The second Default post", "This post was created automatically after cleaning the database", "All Users", "no", new AuthorDto(USER_NAME), false),
//                new PostsDto("The first Default post", "This post was created automatically after cleaning the database", "All Users", "no", new AuthorDto(USER_NAME), false)
                PostsDto.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()

        };

        Assert.assertEquals("Number of posts ", expectedResponseDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostByUserNegative () {
        final String NOT_VALID_USER_NAME = "NotValidUser";
        //method 3 - Response as String
        String actualResponse = apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, 400)
                .extract().response().body().asString(); //зберігаємо респонс як стрінгу

        Assert.assertEquals("Messege in response ",
                "\"Sorry, invalid user requested. Wrong username - "+NOT_VALID_USER_NAME+
                        " or there is no posts. Exception is undefined\"",
                actualResponse);
    }

    @Test
    public void getAllPostByUserPath() {
        //method 4 - json path
        Response actualResponse = apiHelper.getAllPostsByUserRequest(USER_NAME).extract().response();

        SoftAssertions softAssertions = new SoftAssertions();
        List<String> actualListOfTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListOfTitles.size(); i++) {
            softAssertions.assertThat(actualListOfTitles.get(i))
                    .as("Item number " + i)
                    .contains("Default post");

        }

        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);
        for (int i = 0; i < actualAuthorList.size(); i++) {

           softAssertions.assertThat(actualAuthorList.get(i).get("username"))
                   .as("Item number " + i)
                   .isEqualTo(USER_NAME);
        }


        softAssertions.assertAll();
    }

    @Test
    public void getAllPostByUserSchema(){
        apiHelper.getAllPostsByUserRequest(USER_NAME)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("response.json"));

    }

}