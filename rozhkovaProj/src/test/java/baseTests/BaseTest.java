package baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

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
        webDriver.quit();//закриває повністю браузер
        logger.info("Browser was closed");
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
        }
        return webDriver; //відкривання браузера Chrome
    }
}
