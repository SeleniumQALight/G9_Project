package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDto;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostDto;
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
    public void getTokenAndDeletePosts(){
        token = apiHelper.getToken();
        //System.out.println(token);
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API.toLowerCase(), token);

    }

    @Test
    public void createPostByApi(){
        int numberOfPosts = apiHelper.getAllPostsByUserRequest().extract().response().as(PostDto[].class).length;//кількість постів до створення нового посту
        System.out.println(numberOfPosts);

        CreatePostDto createPostDto = CreatePostDto.builder() //створюємо обєкт класу CreatePostDto
                .title("New Post from API")
                .body("New Post Body from API")
                .select1("One Person")
                .uniquePost("yes")
                .token(token) //з біфо секції
                .build();

        String actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDto)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(HttpStatus.SC_OK)//1. перевіряємо, що статус код відповіді 200
                        .log().all()
                        .extract().response().asString();

        Assert.assertEquals("Message in response", "\"Congrats.\"", actualResponse); //пофіксили "Congrats." - було Congrats. або в експектід або в актуал//перевірка, що відповідь містить вказаний текст
    //2. перевірка, що пост створився. треба відпривитии запит на отримання постів і перевірити, що він є і на один пост більше. Треба перед ств посту кількість і після створення кількість

        int numberOfPostsAfterCreatingPost = apiHelper.getAllPostsByUserRequest().extract().response().as(PostDto[].class).length;//кількість постів до створення нового посту

        System.out.println(numberOfPosts);
        System.out.println(numberOfPostsAfterCreatingPost);
        Assert.assertEquals("Number of posts after creating post", numberOfPosts + 1, numberOfPostsAfterCreatingPost);

        //перевірити. що саме з нашим контентом створився пост
        PostDto expectedPostDto =
                PostDto.builder()
                        .title(createPostDto.getTitle())//беремо з обєкту який створили вище
                        .body(createPostDto.getBody())
                        .select(createPostDto.getSelect1())
                        .uniquePost(createPostDto.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder().username(TestData.VALID_LOGIN_API.toLowerCase()).build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(apiHelper.getAllPostsByUserAsDto(TestData.VALID_LOGIN_API.toLowerCase())[0])//метод, який повертає масив постів
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPostDto);
        softAssertions.assertAll();
    }




}
