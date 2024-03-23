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
        WebDriverManager.chromedriver().setup(); //скачує виконуаний файл
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
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm =
                webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("qaauto");
        logger.info("'qaauto' was inputted into into UserName");

        WebElement inputPasswordLoginForm =
                webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");

        Assert.assertTrue("Button Sign out is not visible", isButtonSignOutDisplayed());


    }

    private boolean isButtonSignOutDisplayed() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath("//button[contains(text(),'Sign Out')]")).isDisplayed();
            logger.info(state + " is button displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not existed");
            return false;
        }
        }


    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");
        logger.info("Site was opened");

        WebElement inputUserNameLoginForm = webDriver.findElement
                (By.xpath("//input[@placeholder='Username']"));
        inputUserNameLoginForm.clear();
        inputUserNameLoginForm.sendKeys("12345");
        logger.info("'12345' was inputted into UserName");

        WebElement inputPasswordLoginForm = webDriver.findElement
                (By.xpath(".//input[@placeholder='Password']"));
        inputPasswordLoginForm.clear();
        inputPasswordLoginForm.sendKeys("123456qwerty");
        logger.info("Password '123456qwerty' was inputted");

        webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
        logger.info("Button Sign in was clicked");



        Assert.assertTrue("Button Sign In  in is not visible", isButtonSignInDisplayed());
        Assert.assertFalse("Button Sign Out Sign out is not visible", isButtonSignOutDisplayed());
        Assert.assertTrue("Massage 'Invalid username/password' is not visible", isInvalidUsernamePasswordDisplayed());
    }


    private boolean isButtonSignInDisplayed() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).isDisplayed();
            logger.info(state + " is button Sign in is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Button Sign in is not existed");
            return false;
        }
    }


    private boolean isInvalidUsernamePasswordDisplayed() {
        try {
            boolean state =
                    webDriver.findElement(By.xpath
                            ("//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info(state + " 'Invalid username/password' is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element 'Invalid username/password' is not existed");
            return false;
        }
    }
}


