package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//td[@id='EUR_buy']")
    private WebElement buyRateEuro;

    @FindBy(xpath = "//td[@id='EUR_sell']")
    private WebElement saleRateEuro;

    @FindBy(xpath = "//td[@id='USD_buy']")
    private WebElement buyRateUsd;

    @FindBy(xpath = "//td[@id='USD_sell']")
    private WebElement saleRateUsd;

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
        if (currencyName.equals("USD")) {
            exchangeRate.put("buy", buyRateUsd.getText());
            exchangeRate.put("sale", saleRateUsd.getText());
        } else if (currencyName.equals("EUR")) {
            exchangeRate.put("buy", buyRateEuro.getText());
            exchangeRate.put("sale", saleRateEuro.getText());
        }

        logger.info(exchangeRate);
        return exchangeRate;

    }


}
