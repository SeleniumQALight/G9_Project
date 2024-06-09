package api;

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

public class PrivatbankApiHelper {

    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public Map<String, String> getBuyAndSaleRateFromApi(String currencyName){
        Response response = given()
                .spec(requestSpecification)
                .queryParams("json", "true")
                .queryParams("exchange", "true")
                .queryParams("coursid", "5")
                .when()
                .post(PrivatBankEndpoints.PUBLIC_EXCHANGE_API)
                .then()
                .spec(responseSpecification)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Map<String, String>> rates = jsonPath.getList("");

        Map<String, String> exchangeRateFromApi = new HashMap<>();

        for (Map<String, String> rate : rates) {
            if (rate.get("ccy").equals(currencyName)) {
                exchangeRateFromApi.put("sale", rate.get("sale"));
                exchangeRateFromApi.put("buy", rate.get("buy"));
                break;
            }
        }

        logger.info(exchangeRateFromApi);
        return exchangeRateFromApi;


    }
}
