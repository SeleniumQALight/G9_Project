package privatBankApi;

import api.EndPointsPrivatBank;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PrivatApiHelper {
    private Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


    public Map<String, String> getExchangeRatesFromApi(String currency) {
        String response = given()
                .spec(requestSpecification)
                .when()
                .params("json", "", "exchange", "", "coursid", "5")
                .get(EndPointsPrivatBank.EXCHANGE_RATES_INFO)
                .then()
                .spec(responseSpecification)
                .extract()
                .asString();

        JSONArray jsonArray = new JSONArray(response);
        Map<String, String> exchangeRates = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String ccy = jsonObject.getString("ccy");

            if (ccy.equals(currency)) {
                String buy = jsonObject.getString("buy");
                String sale = jsonObject.getString("sale");

                exchangeRates.put("buy", buy);
                exchangeRates.put("sale", sale);

                logger.info("Currency: " + ccy);
                logger.info("Buy rate: " + buy);
                logger.info("Sell rate: " + sale);
                break;
            }
        }

        return exchangeRates;
    }
}
