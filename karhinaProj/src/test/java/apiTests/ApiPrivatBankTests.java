package apiTests;

import apiPrivatBank.EndPointsPrivatBank;
import apiPrivatBank.dto.responseDto.CurrencyDto;
import apiPrivatBank.dto.responseDto.ExchangeRatesDto;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiPrivatBankTests {
    private Logger logger = Logger.getLogger(getClass());
    private final String DATE = "22.03.2022";

    @Test
    public void getAllExchangeRates() {

        ExchangeRatesDto actualResponseAsDto =
                        given()
                                .contentType(ContentType.JSON)
                                .queryParam("date", DATE)
                                .log().all()
                                .when()
                                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                                .then()
                                .statusCode(200)
                                .extract().body().as(ExchangeRatesDto.class);
        logger.info(actualResponseAsDto.toString());

        ArrayList<CurrencyDto> currencyDto = new ArrayList<>();
        currencyDto.add(new CurrencyDto("UAH", "AUD"));
        currencyDto.add(new CurrencyDto("UAH", "AZN"));
        currencyDto.add(new CurrencyDto("UAH", "BYN"));
        currencyDto.add(new CurrencyDto("UAH", "CAD"));
        currencyDto.add(new CurrencyDto("UAH", "CHF"));
        currencyDto.add(new CurrencyDto("UAH", "CNY"));
        currencyDto.add(new CurrencyDto("UAH", "CZK"));
        currencyDto.add(new CurrencyDto("UAH", "DKK"));
        currencyDto.add(new CurrencyDto("UAH", "EUR"));
        currencyDto.add(new CurrencyDto("UAH", "GBP"));
        currencyDto.add(new CurrencyDto("UAH", "GEL"));
        currencyDto.add(new CurrencyDto("UAH", "HUF"));
        currencyDto.add(new CurrencyDto("UAH", "ILS"));
        currencyDto.add(new CurrencyDto("UAH", "JPY"));
        currencyDto.add(new CurrencyDto("UAH", "KZT"));
        currencyDto.add(new CurrencyDto("UAH", "MDL"));
        currencyDto.add(new CurrencyDto("UAH", "NOK"));
        currencyDto.add(new CurrencyDto("UAH", "PLN"));
        currencyDto.add(new CurrencyDto("UAH", "SEK"));
        currencyDto.add(new CurrencyDto("UAH", "SGD"));
        currencyDto.add(new CurrencyDto("UAH", "TMT"));
        currencyDto.add(new CurrencyDto("UAH", "TRY"));
        currencyDto.add(new CurrencyDto("UAH", "UAH"));
        currencyDto.add(new CurrencyDto("UAH", "USD"));
        currencyDto.add(new CurrencyDto("UAH", "UZS"));



        ExchangeRatesDto expectedResponseDto = new ExchangeRatesDto
                (DATE, "PB", 980, "UAH", currencyDto);


        Assert.assertEquals("Number of exchange rates ", expectedResponseDto.getExchangeRate().size(), actualResponseAsDto.getExchangeRate().size());

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();


    }

}
