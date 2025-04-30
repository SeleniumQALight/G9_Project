package data;

import libs.ConfigProvider;

import java.util.Map;

//This
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login()); //даємо можливість або прописати параметр з командного рядочка (Edit Configuration зверху), або взяти з конфіга
    public static final String VALID_NEW_LOGIN_UI = System.getProperty("defaultloginNew", ConfigProvider.configHiddenProperties.loginNew());
    public static final String VALID_PASSWORD_UI = "123456qwerty";

    public static final String VALID_LOGIN_API = "autotetiana";
    public static final String VALID_PASSWORD_API = "Qwerty123456";





    public static Map<String, String> EXCHANGE_RATE_BY_API;
    public static Map<String, String> EXCHANGE_RATE_BY_UI;
}
