package apiTests;

import apiPrivatBank.EndPointsPrivatBank;
import apiPrivatBank.responseDto.ExchangeRateFieldDto;
import apiPrivatBank.responseDto.ExchangeRatesDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiTestsPrivatBank {

    private Logger logger = Logger.getLogger(getClass());

    private final String DATE = "22.03.2022";

    @Test
    public void getExchangeRates() {
        ExchangeRatesDto actualResponseAsDto =
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("date", DATE)
                        .log().all()
                        .when()
                        .get(EndPointsPrivatBank.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .extract().body().as(ExchangeRatesDto.class);

        ArrayList<ExchangeRateFieldDto> exchangeRateFieldDto = new ArrayList<>();
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "AUD"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "AZN"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "BYN"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "CAD"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "CHF"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "CNY"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "CZK"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "DKK"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "EUR"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "GBP"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "GEL"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "HUF"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "ILS"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "JPY"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "KZT"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "MDL"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "NOK"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "PLN"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "SEK"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "SGD"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "TMT"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "TRY"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "UAH"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "USD"));
        exchangeRateFieldDto.add(new ExchangeRateFieldDto("UAH", "UZS"));


        ExchangeRatesDto expectedResponseAsDto =
                new ExchangeRatesDto(DATE, "PB", 980, "UAH", exchangeRateFieldDto);

        Assert.assertEquals("Number of currencies in the list: ", expectedResponseAsDto.getExchangeRate().size(), actualResponseAsDto.getExchangeRate().size());

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponseAsDto);

        softAssertions.assertAll();
    }
}
