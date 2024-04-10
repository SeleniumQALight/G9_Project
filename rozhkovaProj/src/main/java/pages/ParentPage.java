package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements { //тепер треба в нащадках реалізувати метод getRelativeUrl

    String env = System.getProperty("env", "aqa"); //якщо не вказано у списку ранерів getProperty, то використовується aqa, як дефолтний. 'env' - ключ, 'aqa' - значення
    String baseUrl = String.format("https://%s-complexapp.onrender.com", env); //%s = aqa


    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl(); //абстрактний метод, який повертає релативний URL сторінки, той що після слешу домашньої сторінки

    protected void checkUrl() {  //перевіряє чи поточний URL відповідає релативному URL сторінки. Але не скрізь, бо не можемо сформувати експектед URL. На допомогу регулярні вирази.
        Assert.assertEquals("Invalid page", baseUrl + getRelativeUrl(), webDriver.getCurrentUrl());

    }
    // метод для перевірки чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    //https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]

    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n" + "Expected url: " + baseUrl + getRelativeUrl() + "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));//тру, бо неточний вираз, не рівний
    }

}
