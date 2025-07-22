package apiPB.helpers;

import apiPB.EndPointsPB;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperPB {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public Map<String, String> getExchangeRateByAPI(String currency) {
        Response response = given()
                .spec(requestSpecification)
                .when()
                .params("json", "", "exchange", "", "coursid", "5")
                .get(EndPointsPB.CURRENCY_EXCHANGE_RATES)
                .then()
                .spec(responseSpecification)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Map<String, String>> rates = jsonPath.getList("");

        Map<String, String> exchangeRateByApi = new HashMap<>();

        for (Map<String, String> rate : rates) {
            if (rate.get("ccy").equals(currency)) {
                exchangeRateByApi.put("buy", rate.get("buy"));
                exchangeRateByApi.put("sell", rate.get("sale"));
                break;
            }
        }
        logger.info("Exchange rate by API for " + currency + " is: " + exchangeRateByApi);
        return exchangeRateByApi;
    }


}
