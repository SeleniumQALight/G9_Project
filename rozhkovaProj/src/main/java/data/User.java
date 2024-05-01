package data;

import com.github.javafaker.Faker; //бібліотека для генерації фейкових даних: вигадані імена, адреси, номери телефонів, емейли, паролі. але може бути не унікальним. при тестуванні використовувати дані схожі на реальні
import libs.Util;

public class User {
    private String userName;
    private String email;
    private String password;
    private Faker faker = new Faker();

    public final static String SHORT_PASSWORD_NOT_VALID = "tr";
    public final static String SHORT_EMAIL_NOT_VALID = "tr";

    public final static String USER_NAME_MIN_LENGTH = "t".repeat(3);
    public final static String USER_NAME_MAX_LENGTH = "t".repeat(30);


    public final static String PASSWORD_MIN_LENGTH = "t".repeat(6) + "1".repeat(6);
    public final static String PASSWORD_MAX_LENGTH = "t".repeat(25) + "1".repeat(25);


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String tcNumber) {//кноструктор для тесту з ВАЛІДНИМИ параметрами, використовуємо номер тест кейсу, для унікальності
        this.userName = tcNumber + "RO" + faker.name().lastName() + Util.getDateAndTimeFormattedOnlyNumbers();//util only numbers
        this.email = this.userName + "@gmai.tt";//співставляємо імейл з юзернеймом
        this.password = TestData.VALID_PASSWORD_UI;
    }
    public User(String userName, String password) {
        this.userName = userName;
        this.email = this.userName + "@gmai.tt";//співставляємо імейл з юзернеймом
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User updateUserName(String newUserName) { //дозволяють у згенерованому обєкті змінити юзернейм, емейл, пароль
        this.userName = newUserName;
        return this;
    }

    public User updateEmail(String newEmail) {
        this.email = newEmail;
        return this;
    }

    public User updatePassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @Override
    public String toString() { //формат в якому буде виглядати об'єкт, якщо ми захочемо його роздрукувати
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
