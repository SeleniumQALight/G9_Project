package api.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostsDTO {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty("select1")
    private String select;
    private String uniquePost;
    private String createdDate;

    private AuthorDTO author;
    private Boolean isVisitorOwner;

    public PostsDTO() {
    }

    public PostsDTO(String title, String body, String select, String uniquePost, AuthorDTO author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select = select;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setIsVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }

    @Override
    public String toString() {
        return "PostsDTO{" +
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
