package baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import libs.ScreenShot;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class BaseTest {
    WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();//список скріншотів

    //виконується перед кожним тестом
    @Before
    public void setUp() {
        logger.info("---------" + testName.getMethodName() + " was started---------");
        //WebDriverManager.chromedriver().setup();  //скачує виконуваний файл
       // webDriver = new ChromeDriver(); //відкривання браузера Chrome  замість цього викликаємо метод initDriver
        webDriver = initDriver();//відкривання браузера Chrome пустого
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //дефолтне очікування, протягом 5 сек вебдрайвер буде намагатися виконати дію.
        //кожні півсекунди він буде намагатися клікати на кнопку наприклад. зміг - іде далі. дійде за 5 сек і не зможе клікнути, то впаде помилка
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
       // webDriver.quit();//закриває повністю браузер
       // logger.info("Browser was closed");
        logger.info("---------" + testName.getMethodName() + " was finished---------");
    }

    @Rule //для виведення назви тесту, перед всіма тестами
    public TestName testName = new TestName();


    //initDriver - метод, який ініціалізує драйвер
    private WebDriver initDriver() {
        String browserFromProperty = System.getProperty("browser");
        if ((browserFromProperty == null) || "chrome".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.chromedriver().setup();  //скачує виконуваний файл
            webDriver = new ChromeDriver(); //відкривання браузера Chrome.
        }
        else if ("firefox".equals(browserFromProperty.toLowerCase())){ // -Dbrowser=firefox
            WebDriverManager.firefoxdriver().setup();//скачує виконуваний файл для Firefox
            webDriver = new FirefoxDriver();
        } else if ("ie".equals(browserFromProperty.toLowerCase())){
            WebDriverManager.iedriver().setup(); //zoom 100%
            webDriver = new InternetExplorerDriver(); //security level - Medium
        } else if ("safari".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.safaridriver().setup();
            webDriver = new SafariDriver();
        } else if ("edge".equalsIgnoreCase(browserFromProperty)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        } else if ("remote".equals(browserFromProperty)) { //щоб відправити тест на селеніум град
            logger.info("Remote browser");
            WebDriverManager.chromedriver().setup();//скачає exe файл
            DesiredCapabilities cap = new DesiredCapabilities();//налаштування браузера, наприклад налаштування дефолтної папки для завантаження файлів
            cap.setBrowserName("chrome");
            ChromeOptions chromeOptions = new ChromeOptions();//налаштування браузера, наприклад відключити друк на принтер
            chromeOptions.merge(cap);
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),//адреса, де запущений селеніум грід
                        chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            throw new IllegalArgumentException("Browser " + browserFromProperty + " is not supported");
        }
        return webDriver; //відкривання браузера Chrome
    }
    @Rule() //для скріншотів
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };

    protected void takeScreenshot(String screenShotName) {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(screenShotName, screen));
    }

}
