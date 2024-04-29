package data;

import com.github.javafaker.Faker;
import libs.Util;

import static data.StringConstant.stringLength_2;

public class User {
    private String userName;
    private String email;
    private String passWord;
    private Faker faker = new Faker();

    public final static String USER_NAME_MIN_LENGTH = "t".repeat(3);
    public final static String USER_NAME_MAX_LENGTH = "t".repeat(30);

    public final static String PASSWORD_MIN_LENGTH = "t".repeat(6) + "1".repeat(6);
    public final static String PASSWORD_MAX_LENGTH = "t".repeat(25) + "1".repeat(25);


    public final static String SHORT_PASSWORD = stringLength_2;
    public final static String SHORT_EMAIL = stringLength_2;


    public User(String userName, String email, String passWord) {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
    }

    public User(String tc_number) {
        this.userName = tc_number + faker.name().lastName() + Util.getDateAndTimeFormattedOnlyNumbers();
        this.email = this.userName + "@tt.co";
        this.passWord = TestData.VALID_PASSWORD_UI;
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.email = this.userName + "@tt.co";
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User updatePassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public User updateUsername(String userName) {
        this.userName = userName;
        return this;
    }

    public User updateEmail(String email){
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
