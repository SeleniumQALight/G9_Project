package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responsDto.AuthorDto;
import api.dto.responsDto.PostsDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class CreatePostByApi {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void getTokenAndDeletePosts() {
        token = apiHelper.getToken();
        apiHelper.deleteAllPostsByUserTillPresent(VALID_LOGIN_API, token);

    }

    @Test
    public void createPostByApi() {
        int numberOfPostsBeforeCreatingPosts = apiHelper.getAllPostsByUserRequest()
                .extract().response().as(PostsDto[].class).length;
        System.out.println("Number of posts before " + numberOfPostsBeforeCreatingPosts);

        CreatePostDto createPostDto = CreatePostDto.builder()
                .title("Title Post Api Karhina")
                .body("Body Post Api Karhina")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String actualResponse = given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(createPostDto)
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().asString();

        Assert.assertEquals("Message in response ", "Congrats.", actualResponse.toString().replace("\"", ""));
//        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        int numberOfPostsAfterCreatingPost = apiHelper.getAllPostsByUserRequest()
                .extract().response().as(PostsDto[].class).length;
        System.out.println("Number of posts after " + numberOfPostsAfterCreatingPost);


        Assert.assertEquals("Number of posts",
                numberOfPostsBeforeCreatingPosts + 1, numberOfPostsAfterCreatingPost);

        PostsDto expectedPostsDto =
               PostsDto.builder()
                        .title(createPostDto.getTitle())
                        .body(createPostDto.getBody())
                        .uniquePost(createPostDto.getUniquePost())
                        .select(createPostDto.getSelect1())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder()
                                .username(VALID_LOGIN_API)
                                .build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(apiHelper.getAllPostsByUser(VALID_LOGIN_API)[0])
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                .isEqualTo(expectedPostsDto);
        softAssertions.assertAll();


    }
}
