package apiTests;

import api.ApiHelper;
import api.EndPoints;
import api.dto.responseDto.AuthorDto;
import api.dto.responseDto.PostDto;
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
    public void getAllPostsForUser() {
        //Actual Result
        PostDto[] actualResponseAsDto = //результат зберігаємо в цій змінній, щоб потіім можна кудись передати
                given()//починаємо готувати запит, що отримати пости для користувача: авторизацію, налаштовувати хедери, якщо є
                        .contentType(ContentType.JSON)//вказуємо, що хочемо отримати дані у форматі JSON
                        .log().all()//виводимо в консоль всі дані, які відправляються на сервер

                        .when()//вказуємо, що саме хочемо зробити - дія
                        .get(EndPoints.POSTS_BY_USER, USER_NAME)//вказуємо метод запиту та шлях до ресурсу

                        .then()//після виконання запиту перевіряємо результат
                        .log().all()//виводимо в консоль всі дані, які отримали від сервера
                        .statusCode(200)//перевіряємо, що статус код відповіді 200
                        //method 1 - restassured assert// перевірки, які вбудовані в restassured - недолік, якщо багато філдів, то треба кожний філд перевірити окремо
                        .assertThat().body("[0].title", equalTo("The second Default post")) //перевіряємо: перший пост, поле тайтл, має бути рівним вказаному тексту
                        .body("author.username", everyItem(equalTo(USER_NAME)))//перевіряємо: автор всіх постів має бути вказаного користувача, пройтися по всім постам
                        //method 2 - DTO - Data Transfer Object// з респонсу зробимо java об'єкт, як актуал, і зробимо обєкт як експектід, щою порівняти їх
                        .extract().body().as(PostDto[].class);//витягуємо тіло (у нашому випадку список обєктів) відповіді та перетворюємо його в об'єкт класу PostDto
                                                              //треба геттери і сетттери і пустий конструктор. щоб рест ашуред міг засетити значення в поля

        //method 3 - коли в респонсі стрінг тільки, наприклад невалідний якийсь кейс

        logger.info(actualResponseAsDto[0].toString());
        logger.info("Size = " + actualResponseAsDto.length);
        logger.info("Title [0] = " + actualResponseAsDto[0].getTitle());//достукатися до конкретного поля
        logger.info("username [0] = " + actualResponseAsDto[0].getAuthor().getUsername());//достукатися до конкретного поля з складеного обєкту

//перевірки з обєектом
        //в кожно пості є назва автора, яка дорівнює вказаному користувачу
        //недоліки: тест падає на першому пості, якщо він не відповідає вказаному користувачу. Не побачимо результати для інших постів. Краще використати софт ассерти
        for (int i = 0; i < actualResponseAsDto.length; i++) {
            Assert.assertEquals("Username in post # " + i + " is not matched", USER_NAME, actualResponseAsDto[i].getAuthor().getUsername());

        }

        //Expected Result
        PostDto [] expectedResponseAsDto = {
                /*new PostDto("The second Default post", "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(USER_NAME) ,false),
                new PostDto("The first Default post", "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(USER_NAME) ,false)*/
                PostDto.builder() //створити обєект з наступними полями. не використовувати багато конструкторів якщо нам треба різні поля
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build(),//саме тут створюємо обєкт
                PostDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .isVisitorOwner(false)
                        .build()
        };

//спочатку перевіряємо кількість постів. Якщо співпало, то тоді вже проходимося по всім постам

        Assert.assertEquals("Number of posts", expectedResponseAsDto.length, actualResponseAsDto.length);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto).usingRecursiveComparison() //перевірить всі вкладені обєкти, якщо деякі поля складаються ще з обєктів
                .ignoringFields("id", "createdDate", "author.avatar") //ігноруємо поля, які не важливі для нас
                .isEqualTo(expectedResponseAsDto);

        softAssertions.assertAll();//після всіх перевірок викликаємо метод, який виведе всі помилки, якщо вони є
    }

    @Test //використовуємо method 3 - респонс, як стрінга
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "notValidUser";
        String actualResponse = apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, 400)
                .extract().response().body().asString();//витягуємо тіло відповіді у вигляді стрінги. asString() - метод рест ашурента, перетворює в стрінгу

        Assert.assertEquals("Message in response", "\"Sorry, invalid user requested. Wrong username - "+NOT_VALID_USER_NAME+
                " or there is no posts. Exception is undefined\"", actualResponse);

    }
}
