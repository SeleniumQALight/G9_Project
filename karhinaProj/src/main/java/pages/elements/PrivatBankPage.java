package pages.elements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

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
