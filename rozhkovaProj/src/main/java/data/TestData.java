package data;

import libs.ConfigProvider;

//This
public class TestData {
    public static final String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login()); //даємо можливість або прописати параметр з командного рядочка (Edit Configuration зверху), або взяти з конфіга
    public static final String VALID_PASSWORD_UI = "123456qwerty";
}
