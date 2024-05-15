package apiTest.PrivatBankTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import org.junit.Test;
import privatBankApi.EndPoints;
import privatBankApi.dto.responseDto.BasicInfo;
import privatBankApi.dto.responseDto.ExchangeRate;

import static io.restassured.RestAssured.given;

public class GetExchangeRatesTest {
    private Logger logger = Logger.getLogger(getClass());
    @Test
    public void getExchangeRates(){
        BasicInfo actualExchangeRate =
                given().queryParam("date", "22.03.2022")
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(BasicInfo.class);

        ExchangeRate[] exchangeRates = {
                new ExchangeRate("AUD"),
                new ExchangeRate("AZN"),
                new ExchangeRate("BYN"),
                new ExchangeRate("CAD"),
                new ExchangeRate("CHF"),
                new ExchangeRate("CNY"),
                new ExchangeRate("CZK"),
                new ExchangeRate("DKK"),
                new ExchangeRate("EUR"),
                new ExchangeRate("GBP"),
                new ExchangeRate("GEL"),
                new ExchangeRate("HUF"),
                new ExchangeRate("ILS"),
                new ExchangeRate("JPY"),
                new ExchangeRate("KZT"),
                new ExchangeRate("MDL"),
                new ExchangeRate("NOK"),
                new ExchangeRate("PLN"),
                new ExchangeRate("SEK"),
                new ExchangeRate("SGD"),
                new ExchangeRate("TMT"),
                new ExchangeRate("TRY"),
                new ExchangeRate("UAH"),
                new ExchangeRate("USD"),
                new ExchangeRate("UZS")
        };

                BasicInfo expectedExchangeRate =
                        new BasicInfo("22.03.2022", "PB", 980, "UAH", exchangeRates);


               SoftAssertions softAssertions = new SoftAssertions();

               softAssertions.assertThat(actualExchangeRate)
                       .usingRecursiveComparison()
                       .ignoringFields("exchangeRate")
                       .isEqualTo(expectedExchangeRate);

            softAssertions.assertThat(actualExchangeRate)
                    .usingRecursiveComparison()
                    .ignoringFields("exchangeRate.saleRateNB",
                            "exchangeRate.purchaseRateNB",
                            "exchangeRate.saleRate", "exchangeRate.purchaseRate")
                    .isEqualTo(expectedExchangeRate);
            softAssertions.assertAll();
        }
    }

