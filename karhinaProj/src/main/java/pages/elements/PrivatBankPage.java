package pages.elements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;

import java.util.HashMap;
import java.util.Map;

public class PrivatBankPage extends ParentPage {
    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return null;
    }

    String privatBankMainUrl = "https://privatbank.ua";

    @FindBy(xpath = "//td[@id='USD_buy']")
    private WebElement buyExchangeRateUsd;

    @FindBy(xpath = "//td[@id='USD_sell']")
    private WebElement saleExchangeRateUsd;

    @FindBy(xpath = "//td[@id='EUR_buy']")
    private WebElement buyExchangeRateEuro;

    @FindBy(xpath = "//td[@id='EUR_sell']")
    private WebElement saleExchangeRateEuro;

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

        public Map<String, String> getBuyAndSaleRateFromUI (String currency) {
            openPrivatBankPage();
            Map<String, String> currencyeExchangeRate = new HashMap<>();
            if (currency.equals("USD")) {
                currencyeExchangeRate.put("buy", buyExchangeRateUsd.getText());
                currencyeExchangeRate.put("sale", saleExchangeRateUsd.getText());
            } else if (currency.equals("EUR")) {
                currencyeExchangeRate.put("buy", buyExchangeRateEuro.getText());
                currencyeExchangeRate.put("sale", saleExchangeRateEuro.getText());
            }

            return currencyeExchangeRate;

        }

}
