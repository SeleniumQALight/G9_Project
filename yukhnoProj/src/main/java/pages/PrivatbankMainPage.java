package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class PrivatbankMainPage extends ParentPage{

    public PrivatbankMainPage(WebDriver webDriver) { super(webDriver); }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    private Logger logger = Logger.getLogger(getClass());

    String privatbankUrl = "https://privatbank.ua";

    private String rateCurrency = "//td[@id='%s']";

    @Step
    public void openPrivatbankPage(){
        try {
            webDriver.get(privatbankUrl);
            logger.info("Privatbank was opened with url " + privatbankUrl);
        }catch (Exception e){
            logger.error("Can not open Privatbank " + e);
            Assert.fail("Can not open Privatbank " + e);
        }
    }

    public Map<String, String> getBuyAndSaleRateFromUI(String currencyName) {
        openPrivatbankPage();
        Map<String, String> exchangeRate = new HashMap<>();

        String buyRateLocator = String.format(rateCurrency, currencyName + "_buy");
        String saleRateLocator = String.format(rateCurrency, currencyName + "_sell");

        WebElement buyRateElement = webDriver.findElement(By.xpath(buyRateLocator));
        WebElement saleRateElement = webDriver.findElement(By.xpath(saleRateLocator));

        exchangeRate.put("buy", buyRateElement.getText());
        exchangeRate.put("sale", saleRateElement.getText());

        logger.info(exchangeRate);
        return exchangeRate;

    }


}