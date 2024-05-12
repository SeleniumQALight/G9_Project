package apiTests;

import api.PrivatBankEndpoints;
import api.PrivatBankdto.responsePrvDTO.CurrenciesPbDto;
import api.PrivatBankdto.responsePrvDTO.ExchangeRateDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PrivatBankApiTests {
    private Logger logger = Logger.getLogger(getClass());
    @Test
    public void getExchangeRatesPrivatBank(){
        CurrenciesPbDto actualResponseAsDto =
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("date", "22.03.2022")
                .when()
                .get(PrivatBankEndpoints.EXCHANGE_RATES_API)
                .then()
                .log().all()
                .statusCode(200)
        // validation of fields: date, bank,baseCurrency,baseCurrencyLit
                .assertThat()
                .body("date", equalTo("22.03.2022"))
                .body("bank", equalTo("PB"))
                .body("baseCurrency", equalTo(980))
                .body("baseCurrencyLit", equalTo("UAH"))
        // validation exchangeRate
                .extract().body().as(CurrenciesPbDto.class);

        List<String> currencies = Arrays.asList("AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS");

        List<ExchangeRateDto> exchangeRates = new ArrayList<>();
        for (String currency : currencies) {
            exchangeRates.add(new ExchangeRateDto("UAH", currency));
        }

        CurrenciesPbDto expectedResponseDto =
                new CurrenciesPbDto("22.03.2022", "PB", 980, "UAH", exchangeRates);

        Assert.assertEquals("Number of posts ", expectedResponseDto.getExchangeRate().toArray().length,
                actualResponseAsDto.getExchangeRate().toArray().length);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponseAsDto)
                    .usingRecursiveComparison()          // all objects will be checked, not only one
                        .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB",
                                "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                            .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();

    }
}
