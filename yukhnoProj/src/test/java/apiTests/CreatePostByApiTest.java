package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDTO.AuthorDto;
import api.dto.responseDTO.PostDto;
import data.TestData;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    String token;
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void getTokenAndDeletePost(){
        token = apiHelper.getToken();
//        System.out.println(token);
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, token);
    }

    @Test
    public void createPostByApi(){
        int numberOfPosts = apiHelper.getAllPostsByUserRequest()
                .extract()
                .response()
                .as(PostDto[].class).length;

        CreatePostDto createPostDtoBody = CreatePostDto
                .builder()
                .title("Post from api")
                .body("Post body api")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .log().all()
                        .extract().response().body().asString().replace("\"",""); // or "\"Congrats.\""

        Assert.assertEquals("Message in response ", "Congrats.", actualResponse);

        int numberOfPostsAfterCreatingPost = apiHelper.getAllPostsByUserRequest()
                .extract()
                .response()
                .as(PostDto[].class).length;

        System.out.println(numberOfPosts);
        System.out.println(numberOfPostsAfterCreatingPost);
        Assert.assertEquals("Number of posts ", numberOfPosts + 1, numberOfPostsAfterCreatingPost);

        PostDto expectedPostDto =
                PostDto.builder()
                        .title(createPostDtoBody.getTitle())
                        .body(createPostDtoBody.getBody())
                        .select(createPostDtoBody.getSelect1())
                        .uniquePost(createPostDtoBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder()
                                .username(TestData.VALID_LOGIN_API)
                                .build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUser(TestData.VALID_LOGIN_API)[0])
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedPostDto);
        softAssertions.assertAll();

    }
}
