package apiTest.PrivatBankTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import privatBankApi.EndPoints;
import privatBankApi.dto.responseDto.BasicInfo;


import static io.restassured.RestAssured.given;

public class ValidationRatesTest {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRates() {
        BasicInfo actualExchangeRate =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().body().as(BasicInfo.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < actualExchangeRate.getExchangeRate().length; i++) {
            if (actualExchangeRate.getExchangeRate()[i].getSaleRateNB() != null){
                softAssertions.assertThat(actualExchangeRate.getExchangeRate()[i].getSaleRateNB() > 0);
                logger.info("SaleRateNB = " + actualExchangeRate.getExchangeRate()[i].getSaleRateNB() +  " is greater than 0 for index " + i);
            }
            if (actualExchangeRate.getExchangeRate()[i].getPurchaseRateNB() != null){
                softAssertions.assertThat(actualExchangeRate.getExchangeRate()[i].getPurchaseRateNB() > 0);
                logger.info("PurchaseRateNB = " + actualExchangeRate.getExchangeRate()[i].getPurchaseRateNB() + " is greater than 0 for index " + i);
            }
            if (actualExchangeRate.getExchangeRate()[i].getSaleRate() != null){
                softAssertions.assertThat(actualExchangeRate.getExchangeRate()[i].getSaleRate() > 0);
                logger.info("SaleRate = " + actualExchangeRate.getExchangeRate()[i].getSaleRate() + " is greater than 0 for index " + i);
            }
            if (actualExchangeRate.getExchangeRate()[i].getPurchaseRate() != null){
                softAssertions.assertThat(actualExchangeRate.getExchangeRate()[i].getPurchaseRate() > 0);
                logger.info("PurchaseRate = " + actualExchangeRate.getExchangeRate()[i].getPurchaseRate() + " is greater than 0 for index " + i);
            }
        }
        softAssertions.assertAll();


    }
}
