package api.dto.responseDto;

import lombok.*;

@Getter// будуть згенеровані всі геттери і сеттери автоматично за допомогою lombok
@Setter//скоротиливсі геттеи і серетери
@ToString
@NoArgsConstructor//скоротили дефолтний конструктор
@AllArgsConstructor//скоротили конструктор з усіма полями
@Builder//створюємо білдер з вказаними полями

public class AuthorDto { //якого типу буде наш автор у PostDto, бо поле автор складається з обєкту з полями username та avatar
    private String username;
    private String avatar;

   /* public AuthorDto() {
    }*///замінили на @NoArgsConstructor

    /*public AuthorDto(String username) {
        this.username = username;
    }*///замінили на @AllArgsConstructor @Builder
/*
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
    }*/
}
