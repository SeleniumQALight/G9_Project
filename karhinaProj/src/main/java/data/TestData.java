package data;

// This class is used to store test data

import libs.ConfigProvider;

import java.util.Map;

public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("login", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";
    public static final String VALID_NEW_LOGIN_UI = System.getProperty("loginNew", ConfigProvider.configHiddenProperties.loginNew());

    public static final String VALID_LOGIN_API = "qakarhina";
    public static final String VALID_PASSWORD_API = "380980146690";

    public static final String VALID_LOGIN_API_DEMO_QA = "Olena";
    public static final String VALID_PASSWORD_API_DEMO_QA = "Olena@0980146690";

    public static Map<String, String> EXCHANGE_RATE_BY_API;
    public static Map<String, String> EXCHANGE_RATE_BY_UI;

}
