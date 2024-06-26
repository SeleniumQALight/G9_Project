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

public class LoginTestsAllActionInOneClass {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup(); // скачує виконуваний файл
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
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto");
        logger.info("'qaauto was inputted into input UserName'");

        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("Button Sign out is not visible", isButtonSignOutDisplayed());

    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto");
        logger.info("'qaauto was inputted into input UserName'");

        WebElement inputPasswordLoginForm = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPasswordLoginForm.sendKeys("123456qwerty1");
        logger.info("Password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("Button Sign out is visible", isButtonSignOutDisplayed());
        Assert.assertTrue("Button Sign In is not visible", isButtonSignInDisplayed());
        Assert.assertTrue("Message is not displayed", isErrorMessageDisplayed());

    }

    private boolean isErrorMessageDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " is message displayed");
            return state;
        } catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }
    }

    private boolean isButtonSignInDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e){
            logger.info("'Sign in' is not visible");
        return false;
    }
    }

    private boolean isButtonSignOutDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e){
            logger.info("'Sign out' is not visible");
            return false;
        }
    }
}
