package loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllActionsInOneClass {

    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();  //скачує виконуваний файл
        webDriver = new ChromeDriver(); //відкривання браузера
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //дефолтне очікування, протягом 5 сек вебдрайвер буде намагатися виконати дію.
        //кожні півсекунди він буде намагатися клікати на кнопку наприклад. зміг - іде далі. дійде за 5 сек і не зможе клікнути, то впаде помилка
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();//закриває повністю браузер
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        //input [@placeholder = 'Username']
        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input [@placeholder = 'Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto");//вводимо по одній літері
        logger.info("'qaauto' was inputted into input UserName");

        //input[@placeholder='Password']
        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted");

        //button[contains(text(),'Sign In')]
        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign out is not visible", isButtonSignOutDisplayed()); //виводиться протилежне повідомлення

    }

    private boolean isButtonSignOutDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }
    }
}
