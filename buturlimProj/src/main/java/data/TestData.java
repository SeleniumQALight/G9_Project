package data;

import pages.libs.ConfigProvider;

// This class is used to store test data
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String VALID_LOGIN_UI_NEW = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.loginNew());
}
