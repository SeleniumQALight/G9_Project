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

import javax.sound.midi.Soundbank;

import static data.TestData.VALID_LOGIN_API;
import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void getTokenAndDeletePost(){
        token =apiHelper.getToken();
//        System.out.println(token);
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API, token);
    }

    @Test
    public void createPostByApi(){
        int numberOfPosts = apiHelper.getAllPostsByUserRequest()
                .extract().response().as(PostsDto[].class).length;

        CreatePostDto createPostDtoBody =
                CreatePostDto.builder()
                        .title("Post from api")
                        .body("Post body")
                        .select1("One Person")
                        .uniquePost("Yes")
                        .token(token)
                        .build();

        String actualResponse =
                given()
                        .contentType((ContentType.JSON))
                        .log().all()
                        .body(createPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        int numberOfPostsAfterCreatingPost = apiHelper.getAllPostsByUserRequest()
                .extract().response().as(PostsDto[].class).length;
        Assert.assertEquals("Number of posts"
                , numberOfPosts +1
                , numberOfPostsAfterCreatingPost);

        PostsDto expectedPostDto =
                PostsDto.builder()
                        .title(createPostDtoBody.getTitle())
                        .body(createPostDtoBody.getBody())
                        .select(createPostDtoBody.getSelect1())
                        .uniquePost(createPostDtoBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(
                                AuthorDto.builder()
                                        .username(VALID_LOGIN_API)
                                        .build())
                        .build();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUser(VALID_LOGIN_API)[0])
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isNotEqualTo(expectedPostDto);
        softAssertions.assertAll();
    }


}
