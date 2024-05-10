package apiTests;

import api.PrivatBankEndpoints;
import api.PrivatBankdto.responsePrvDTO.currenciesPbDto;
import api.PrivatBankdto.responsePrvDTO.exchangeRateDto;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PrivatBankApiTests {
    @Test
    public void getExchangeRatesPrivatBank(){
        currenciesPbDto actualResponseAsDto =
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
                .extract().body().as(currenciesPbDto.class);

        List<String> currencies = Arrays.asList("AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD", "TMT", "TRY", "UAH", "USD", "UZS");

        List<exchangeRateDto> exchangeRates = new ArrayList<>();
        for (String currency : currencies) {
            exchangeRates.add(new exchangeRateDto("UAH", currency));
        }

        currenciesPbDto expectedResponseDto =
                new currenciesPbDto("22.03.2022", "PB", 980, "UAH", exchangeRates);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponseAsDto)
                    .usingRecursiveComparison()          // all objects will be checked, not only one
                        .ignoringFields("saleRateNB", "purchaseRateNB", "saleRate", "purchaseRate")
                            .isEqualTo(expectedResponseDto);

        softAssertions.assertAll();

    }
}
