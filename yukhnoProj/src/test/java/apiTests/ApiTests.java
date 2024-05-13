package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDTO.AuthorDto;
import api.dto.responseDTO.PostDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests {
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    private ApiHelper apiHelper = new ApiHelper();
    @Test
    public void getAllPostsForUser(){
        PostDto[] actualResponseAsDto =
                given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME)
                .then()
                .log().all()
                .statusCode(200)
         // method one - restassured assert
                .assertThat()
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
         // method two - DTO data transfer object
                .extract().body().as(PostDto[].class)

        ;
        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());
        logger.info("username [0] = " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("UserName is not matched in post " + i
                , USER_NAME
                , actualResponseAsDto[i].getAuthor().getUsername());
        }

        //Expected result
        PostDto[] expectedResponseDto = {
//                new PostDto("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users",
//                        "no",
//                        new AuthorDto(USER_NAME),
//                        false),
//                new PostDto("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users",
//                        "no",
//                        new AuthorDto(USER_NAME),
//                        false)

                PostDto.builder()
                        .title("The second Default post").body("This post was created automatically after cleaning the database")
                        .select("All Users").uniquePost("no")
                        .isVisitorOwner(false).author(AuthorDto.builder().username(USER_NAME).build())
                        .build(),
                PostDto.builder()
                        .title("The first Default post").body("This post was created automatically after cleaning the database")
                        .select("All Users").uniquePost("no")
                        .isVisitorOwner(false).author(AuthorDto.builder().username(USER_NAME).build())
                        .build()

        };

        Assert.assertEquals("Number of posts ", expectedResponseDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponseAsDto)
                        .usingRecursiveComparison()          // all objects will be checked, not only one
                            .ignoringFields("id", "createdDate", "author.avatar")
                                    .isEqualTo(expectedResponseDto);

        softAssertions.assertAll(); //important line


    }

    @Test
    public void getAllPostsByUserNegative() {
        final String NOT_VALID_USER_NAME = "NotValidUser";
        String actualResponse = apiHelper
                .getAllPostsByUserRequest(NOT_VALID_USER_NAME, 400)
                .extract().response().body().asString();

        Assert.assertEquals(
                "Message in response"
                ,"\"Sorry, invalid user requested. Wrong username - "+NOT_VALID_USER_NAME+
                        " or there is no posts. Exception is undefined\""
                ,actualResponse
        );

    }











}
