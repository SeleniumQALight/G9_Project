package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    private String sellRateLocator = ".//td[@id='%s_sell']";
    private String buyRateLocator = ".//td[@id='%s_buy']";

    public PrivatBankPage checkIsRedirectOnPrivatBankPage() {
        checkUrlForPrivatBank();
        return this;
    }

    public void openPrivatBankPage() {
        try {
            webDriver.get(privatUrl);
            logger.info("PrivatBank page was opened with URL " + privatUrl);
        } catch (Exception e) {
            logger.error("Can not open PrivatBank Page" + e);
            Assert.fail("Can not open PrivatBank Page" + e);
        }
    }

    public Map<String, String> getExchangeRatesFromUI(String currency) {
        openPrivatBankPage();
        Map<String, String> exchangeRates = new HashMap<>();

        String buy = String.format(buyRateLocator, currency);
        String sell = String.format(sellRateLocator, currency);

        String buyRate = webDriver.findElement(By.xpath(buy)).getText();
        String sellRate = webDriver.findElement(By.xpath(sell)).getText();

        exchangeRates.put("buy", buyRate);
        exchangeRates.put("sale", sellRate);
        return exchangeRates;
    }


}
