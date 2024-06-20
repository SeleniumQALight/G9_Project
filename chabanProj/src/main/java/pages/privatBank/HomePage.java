package pages.privatBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;
import privatBankApi.EndPoints;
import lombok.*;


@Getter
@Setter


public class HomePage extends ParentPage {
    @FindBy(xpath = ".//div[@data-cource_type='posts_course']")
    private WebElement courseContainer;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return  "/";
    }

    private String currencyLocator = ".//*[@id='%s_%s']";

    public HomePage scrollToCourseContainer(){
        scrollToElement(courseContainer);
        return this;
    }
    public HomePage openHomePage() {
        try{
            webDriver.get(EndPoints.BASE_URL_PRIVAT_BANK);
            logger.info("Home page was opened with URL " + EndPoints.BASE_URL_PRIVAT_BANK);

        }catch (Exception e){
            logger.error("Can not open login page " + e);
        }
        return this;
    }
    public Double getCurrency (String currency, String operation) {
        String locator = String.format(currencyLocator, currency, operation);
        WebElement element = webDriver.findElement(By.xpath(locator));
        logger.info(operation + " rate for " + currency + " in UI is " + Double.parseDouble(element.getText()));
        return Double.parseDouble(element.getText());


    }
}












