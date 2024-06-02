package pages;

import org.junit.Assert;
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

    @FindBy(xpath = ".//td[@id='USD_buy']")
    private WebElement usdBuyRate;

    @FindBy(xpath = ".//td[@id='USD_sell']")
    private WebElement usdSellRate;

    @FindBy(xpath = ".//td[@id='EUR_buy']")
    private WebElement eurBuyRate;

    @FindBy(xpath = ".//td[@id='EUR_sell']")
    private WebElement eurSellRate;

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
        if (currency.equals("USD")) {
            exchangeRates.put("buy", usdBuyRate.getText());
            exchangeRates.put("sale", usdSellRate.getText());
        } else if (currency.equals("EUR")) {
            exchangeRates.put("buy", eurBuyRate.getText());
            exchangeRates.put("sale", eurSellRate.getText());
        }
        return exchangeRates;
    }


}
