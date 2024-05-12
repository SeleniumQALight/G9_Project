package apiTest;

import api.EndPoints;
import api.dto.responceDto.AuthorDto;
import api.dto.responceDto.PostsDto;
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
    @Test
    public void getAllPostsForUser(){
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
        //method 2 -  DTO (data transfer object)
                .extract().body().as(PostsDto[].class)
        ;
        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());
        logger.info("Username [0] = " + actualResponseAsDto[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("Username is not matched in post " + i
                    , USER_NAME
                    , actualResponseAsDto[i].getAuthor().getUsername());

        }

//        Expected result
        PostsDto[] expectedResponseDto = {
                new PostsDto("The second Default post","This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(USER_NAME), false),
                new PostsDto("The first Default post","This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(USER_NAME), false)
        };
        Assert.assertEquals("Number of posts " , expectedResponseDto.length, actualResponseAsDto.length);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualResponseAsDto)
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();



    }
}
