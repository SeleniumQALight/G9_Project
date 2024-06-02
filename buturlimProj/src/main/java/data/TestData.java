package data;

import pages.libs.ConfigProvider;

import java.util.Map;

// This class is used to store test data
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String VALID_LOGIN_UI_NEW = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.loginNew());
    public static final String VALID_LOGIN_API = "mariiabapi";
    public static final String VALID_PASSWORD_API = "qwerty123456";
    public static final String DEMO_QA_VALID_LOGIN = "yesmabu";
    public static final String DEMO_QA_VALID_PASSWORD = "@Qwerty12345()";


    public static Map<String, String> EXCHANGE_RATE_API;
    public static Map<String, String> EXCHANGE_RATE_UI;
}
