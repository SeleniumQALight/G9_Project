package data;

import libs.ConfigProvider;

import java.util.Map;

// This class is used to store test data
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String VALID_NEW_LOGIN_UI = System.getProperty("loginNew", ConfigProvider.configHiddenProperties.loginNew());

    public static final String VALID_LOGIN_API = "qaira";
    public static final String VALID_PASSWORD_API = "123456qwerty";

    public static final String VALID_LOGIN_API_DEMO_QA = "qaira";
    public static final String VALID_PASSWORD_API_DEMO_QA = "@123456Qwerty()";

    public static Map<String, String> EXCHANGE_RATE_BY_API;
    public static Map<String, String> EXCHANGE_RATE_BY_UI;

}
