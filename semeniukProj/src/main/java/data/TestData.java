package data;


import libs.ConfigProvider;

// This class is used to store test data
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "semeniukAPI";
    public static final String VALID_PASSWORD_API = "semeniukAPI12345";

    public static final String DEMO_QA_VALID_LOGIN = "semeniukDemoAPI";
    public static final String DEMO_QA_VALID_PASSWORD = "@Semeniuk()12345";
}
