package api.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDto {
    //всі поля ссилочні мають бути, щоб якщо поле не прийде, то було налл
    //розібрати наш респонс, як ми описуємо нижче. замапити json на java обєкт
    @JsonProperty("_id")//щоб було поле без _ у назві методу - в json
    private String id;//це вже назва в джаві, для методів з якими будемо працювати в коді
    private String title;
    private String body;
    @JsonProperty("select1")//щоб було поле без 1
    private String select;
    private String uniquePost;
    private String createdDate;

    private AuthorDto author;
    private Boolean isVisitorOwner;

    public PostDto() { //дефолтний конструктор
    }

    public PostDto(String title, String body, String select, String uniquePost, AuthorDto author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select = select;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

    //обовязково треба геттери і сеттери для всіх полів, щоб можна було звертатися до них з інших класів і до них звертається софт ассершен
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getUniquePost() {
        return uniquePost;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setIsVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }

    @Override
    public String toString() {//відображення обєкта в консольці
        return "PostDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select='" + select + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }
}
