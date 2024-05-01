package data;

// This class is used to store test data

import libs.ConfigProvider;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("login", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String VALID_NEW_LOGIN_UI = System.getProperty("loginNew", ConfigProvider.configHiddenProperties.loginNew());



}
