package apiTests;

import api.dto.responseDto.ExchangeRateDto;
import api.dto.responseDto.PrivatBankDto;
import api.EndPointsPrivatBank;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ApiTestPrivatBank {
    final String BASE_CURRENCY = "UAH";
    private Logger logger = Logger.getLogger(getClass());
    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void GetCourses() throws JsonProcessingException {
        String jsonResponseBody =
                given().queryParam("date", "22.03.2022")
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPointsPrivatBank.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().asString();

        PrivatBankDto actualResponseAsDto = objectMapper.readValue(jsonResponseBody, PrivatBankDto.class);

        ExchangeRateDto[] exchangeRatesArray = {
                new ExchangeRateDto(BASE_CURRENCY, "AUD"),
                new ExchangeRateDto(BASE_CURRENCY, "AZN"),
                new ExchangeRateDto(BASE_CURRENCY, "BYN"),
                new ExchangeRateDto(BASE_CURRENCY, "CAD"),
                new ExchangeRateDto(BASE_CURRENCY, "CHF"),
                new ExchangeRateDto(BASE_CURRENCY, "CNY"),
                new ExchangeRateDto(BASE_CURRENCY, "CZK"),
                new ExchangeRateDto(BASE_CURRENCY, "DKK"),
                new ExchangeRateDto(BASE_CURRENCY, "EUR"),
                new ExchangeRateDto(BASE_CURRENCY, "GBP"),
                new ExchangeRateDto(BASE_CURRENCY, "GEL"),
                new ExchangeRateDto(BASE_CURRENCY, "HUF"),
                new ExchangeRateDto(BASE_CURRENCY, "ILS"),
                new ExchangeRateDto(BASE_CURRENCY, "JPY"),
                new ExchangeRateDto(BASE_CURRENCY, "KZT"),
                new ExchangeRateDto(BASE_CURRENCY, "MDL"),
                new ExchangeRateDto(BASE_CURRENCY, "NOK"),
                new ExchangeRateDto(BASE_CURRENCY, "PLN"),
                new ExchangeRateDto(BASE_CURRENCY, "SEK"),
                new ExchangeRateDto(BASE_CURRENCY, "SGD"),
                new ExchangeRateDto(BASE_CURRENCY, "TMT"),
                new ExchangeRateDto(BASE_CURRENCY, "TRY"),
                new ExchangeRateDto(BASE_CURRENCY, "UAH"),
                new ExchangeRateDto(BASE_CURRENCY, "USD"),
                new ExchangeRateDto(BASE_CURRENCY, "UZS"),
        };

        List<ExchangeRateDto> exchangeRatesList = new ArrayList<>(Arrays.asList(exchangeRatesArray));

        PrivatBankDto expectedResponseAsDto =
                new PrivatBankDto("22.03.2022", "PB", 980, BASE_CURRENCY, exchangeRatesList)
        ;


        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponseAsDto)
                .usingRecursiveComparison()
                .ignoringFields("exchangeRate.saleRateNB", "exchangeRate.purchaseRateNB", "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                .isEqualTo(expectedResponseAsDto);

        softAssertions.assertAll();
    }
}