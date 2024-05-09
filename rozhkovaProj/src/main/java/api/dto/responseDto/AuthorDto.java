package api.dto.responseDto;


public class AuthorDto { //якого типу буде наш автор у PostDto, бо поле автор складається з обєкту з полями username та avatar
    private String username;
    private String avatar;

    public AuthorDto() {
    }

    public AuthorDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
