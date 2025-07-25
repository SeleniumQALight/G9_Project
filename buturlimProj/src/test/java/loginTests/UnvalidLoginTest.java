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

public class UnvalidLoginTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup(); // завантажує виконуваний файл
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }


    @Test
    public void unvalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("notvalidname");
        logger.info("'notvalidname' was inputted into input Username");

        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("'123456qwerty' was inputted into input Password");

        webDriver.findElement(By.xpath(".//button[contains(text(), 'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign In is not visible", isButtonSignInDisplayed());
        Assert.assertFalse("Button Sign Out is not visible", isButtonSignOutDisplayed());
        Assert.assertTrue("Error is not visible", isErrorDisplayed());

    }
    private boolean isButtonSignInDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//header//form//button")).isDisplayed();
            logger.info(state + " :Sign In button displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element isn`t visible");
            return false;
        }
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " :Sign Out button displayed");
            return state;
        } catch (Exception e) {
            logger.info("Sign Out button isn`t visible");
            return false;
        }
    }

    private boolean isErrorDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " :error displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element isn`t visible");
            return false;
        }
    }

}
