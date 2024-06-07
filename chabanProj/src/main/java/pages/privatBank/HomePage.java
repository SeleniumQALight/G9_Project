package pages.privatBank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;
import privatBankApi.EndPoints;
import lombok.*;

import java.util.HashMap;

@Getter
@Setter


public class HomePage extends ParentPage {
    @FindBy(xpath = ".//div[@data-cource_type='posts_course']")
    private WebElement courseContainer;

    @FindBy(xpath = ".//*[@id='EUR_buy']")
    private WebElement eurBuy;

    @FindBy(xpath = ".//*[@id='EUR_sell']")
    private WebElement eurSell;

    @FindBy(xpath = ".//*[@id='USD_buy']")
    private WebElement usdBuy;

    @FindBy(xpath = ".//*[@id='USD_sell']")
    private WebElement usdSell;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return null;
    }

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
    public Double getCurrency (String currency, String operation){
    HashMap<String, Double> rates = new HashMap<>();
    rates.put("EUR_buy", Double.parseDouble(getEurBuy().getText()));
    rates.put("EUR_sell", Double.parseDouble(getEurSell().getText()));
    rates.put("USD_buy", Double.parseDouble(getUsdBuy().getText()));
    rates.put("USD_sell", Double.parseDouble(getUsdSell().getText()));


        String key = currency.toUpperCase() + "_" + operation.toLowerCase();
        if (rates.containsKey(key)) {
            logger.info(operation + " rate for " + currency + " in UI is " + rates.get(key));
            return rates.get(key);
        } else {
            throw new IllegalArgumentException("Invalid currency or operation: " + currency + ", " + operation);
        }
    }
}












