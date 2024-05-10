package apiTest;

import api.EndPoints;
import api.dto.responseDTO.AuthorDTO;
import api.dto.responseDTO.PostsDTO;
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
    public void getAllPostsForUser() {
        PostsDTO[] actualResponseAsDTO = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME)
                .then()
                .log().all()
                .statusCode(200)
                // method 1 - rest assured assert
                .assertThat()
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(USER_NAME)))
                // method 2 - DTO - data transfer object
                .extract().body().as(PostsDTO[].class);

        logger.info(actualResponseAsDTO[0].toString());
        logger.info("Size = " + actualResponseAsDTO.length);
        logger.info("Title [0] = " + actualResponseAsDTO[0].getTitle());
        logger.info("username [0] = " + actualResponseAsDTO[0].getAuthor().getUsername());
       for (int i = 0; i < actualResponseAsDTO.length; i++) {
            Assert.assertEquals("Username is not matched in post " + i
                    , USER_NAME
                    , actualResponseAsDTO[i].getAuthor().getUsername());
       }

       // Exepcted result

        PostsDTO[] expectedResponseDTO = {
               new PostsDTO("The second Default post", "This post was created automatically after cleaning the database",
                       "All Users", "no", new AuthorDTO(USER_NAME), false),
                new PostsDTO("The first Default post", "This post was created automatically after cleaning the database",
                "All Users", "no", new AuthorDTO(USER_NAME), false)
        };

        Assert.assertEquals("Number of posts ", expectedResponseDTO.length, actualResponseAsDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponseAsDTO).usingRecursiveComparison()
                        .ignoringFields("id", "createdDate", "author.avatar", "isVisitorOwner")
                .isEqualTo(expectedResponseDTO);




        softAssertions.assertAll();
}
}
