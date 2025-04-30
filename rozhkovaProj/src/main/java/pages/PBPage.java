package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class PBPage extends ParentPage {
    public PBPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    String pBMainUrl = "https://privatbank.ua";
    private static final String CURRENCY_LOCATOR_TEMPLATE = "//td[@id=\"%s_%s\"]";

    @Step
    public void openPBPage() {
        try {
            webDriver.get(String.valueOf(pBMainUrl));
            logger.info("PrivatBank page was opened with URL: " + pBMainUrl);
        } catch (Exception e) {
            logger.error("Can't open PrivatBank page with URL: " + pBMainUrl + " " + e);
            Assert.fail("Can't open PrivatBank page with URL: " + pBMainUrl + " " + e);
        }
    }

    public Map<String, String> getCurrencyRatesFromUI(String currency) {
        openPBPage();
        Map<String, String> currencyRatesFromUI = new HashMap<>();
        String buyRateLocator = String.format(CURRENCY_LOCATOR_TEMPLATE, currency, "buy");
        String sellRateLocator = String.format(CURRENCY_LOCATOR_TEMPLATE, currency, "sell");

        WebElement buyRateElement = webDriver.findElement(By.xpath(buyRateLocator));
        WebElement sellRateElement = webDriver.findElement(By.xpath(sellRateLocator));

        currencyRatesFromUI.put("buy", buyRateElement.getAttribute("innerText").replaceAll("\n","").trim());
        currencyRatesFromUI.put("sell", sellRateElement.getAttribute("innerText").replaceAll("\n","").trim());
        logger.info("Exchange rate from UI for " + currency + " is: " + currencyRatesFromUI);

        return currencyRatesFromUI;

    }
}