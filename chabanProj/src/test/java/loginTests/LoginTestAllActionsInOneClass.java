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
    public void setUp(){
        WebDriverManager.chromedriver().setup(); //скачує виконуваний файл
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

        WebElement inputUserNameLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto");
        logger.info("'qaauto' was inserted into Username filed");
        WebElement inputPasswordLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inserted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign Out is not visible ",isButtonSignOutDisplayed());

    }

    private boolean isButtonSignOutDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not visible");
            return false;
        }
    }

    @Test
    public void invalidLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto432");
        logger.info("'qaauto' was inserted into Username filed");
        WebElement inputPasswordLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inserted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign In is not visible ",isButtonSignInDisplayed());
        Assert.assertFalse("Button Sing out is visible", isButtonSignOutDisplayed() );
        Assert.assertTrue("Pop up isn't displayed", isPopUpDisplayed());

    }
    private boolean isButtonSignInDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not visible");
            return false;
        }

    }
    private boolean isPopUpDisplayed(){
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[contains(text(), 'Invalid username/password.')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        }catch (Exception e){
            logger.info("Element is not visible");
            return false;
        }
    }



}

