package pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.Map;

public class PrivatBankMainPage extends ParentPage{

    public PrivatBankMainPage(WebDriver webDriver) { super(webDriver); }

    @Override
    protected String getRelativeUrl() {
        return "";
    }
    private Logger logger = Logger.getLogger(getClass());
    String privatbankUrl = "https://privatbank.ua";

    String privatBankMainUrl = "https://privatbank.ua";
    private String currencyLocator = "//td[@id='%s_%s']";

    @Step
    public void openPrivatBankPage() {
        try {
            webDriver.get(String.valueOf(privatBankMainUrl));
            logger.info("PrivatBank Page was opened with URL " + privatBankMainUrl);
        } catch (Exception e) {
            logger.error("Can not open PrivatBank Page " + e);
            Assert.fail("Can not open PrivatBank Page " + e);
        }
    }

    public Map<String, String> getBuyAndSaleRateFromUI(String currency) {
        openPrivatBankPage();
        Map<String, String> currencyExchangeRate = new HashMap<>();

        String buyLocator = String.format(currencyLocator, currency, "buy");
        String sellLocator = String.format(currencyLocator, currency, "sell");

        WebElement buyElement = webDriver.findElement(By.xpath(buyLocator));
        WebElement sellElement = webDriver.findElement(By.xpath(sellLocator));

        currencyExchangeRate.put("buy", buyElement.getText());
        currencyExchangeRate.put("sale", sellElement.getText());
        return currencyExchangeRate;
    }
}
