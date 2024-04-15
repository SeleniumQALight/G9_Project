package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

abstract public class ParentPage extends CommonActionsWithElements { //тепер треба в нащадках реалізувати метод getRelativeUrl

    String env = System.getProperty("env", "aqa"); //якщо не вказано у списку ранерів getProperty, то використовується aqa, як дефолтний. 'env' - ключ, 'aqa' - значення
    //String baseUrl = String.format("https://%s-complexapp.onrender.com", env); //%s = aqa
    String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", env); //замість строки вище, урл з файліка config.properties. замінити [env], на те що зчитати у енві

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

    //Open new tab in browser using JavaScript
    public void openNewTabAndSwitchToIt() {
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        logger.info("New tab was opened");
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabs.size() - 1));
        logger.info("Switched to new tab");
    }

    public void switchToMainTab() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        logger.info("Switched to main tab");
    }

    public void closeActiveTabAndSwitchToMainTab() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.close();
        logger.info("New tab was closed");
        webDriver.switchTo().window(tabs.get(0));
        logger.info("Switched to main tab");

    }
    public void switchToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(tabIndex));
        logger.info("Switched to tab with index " + tabIndex);
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }
}
