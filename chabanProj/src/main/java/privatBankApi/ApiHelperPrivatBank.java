package privatBankApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;

import privatBankApi.dto.responseDto.CurrencyAndExchangeRates;

import java.util.HashMap;


import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {
    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public CurrencyAndExchangeRates[]  getExchangeRatesAPI() {
        return given()
                .queryParam("json", "")
                .queryParam("exchange", "")
                .queryParam("coursid", "5")
                .spec(requestSpecification)
                .when()
                .get(EndPoints.PUBINFO)
                .then()
                .spec(responseSpecification)
                .extract().body().as(CurrencyAndExchangeRates[].class);
    }



        public Double mapAndGetRate(String operation,String currency ){
            CurrencyAndExchangeRates[] currencyAndExchangeRates = getExchangeRatesAPI();
        HashMap<String, Double> currencyAndExchangeRatesMap = new HashMap<>();
        for (int i = 0; i < currencyAndExchangeRates.length; i++) {
            if(operation.equals("buy") ){
                currencyAndExchangeRatesMap.put(currencyAndExchangeRates[i].getCcy(), currencyAndExchangeRates[i].getBuy());
            }
            if(operation.equals("sell")){
                currencyAndExchangeRatesMap.put(currencyAndExchangeRates[i].getCcy(), currencyAndExchangeRates[i].getSale());
            }
        }
        logger.info(operation + " rate for " + currency + " in API is " + currencyAndExchangeRatesMap.get(currency));
        return currencyAndExchangeRatesMap.get(currency);
        }
    }

