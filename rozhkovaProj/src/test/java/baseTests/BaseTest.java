package baseTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    //виконується перед кожним тестом
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();  //скачує виконуваний файл
        webDriver = new ChromeDriver(); //відкривання браузера
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
    }
}
