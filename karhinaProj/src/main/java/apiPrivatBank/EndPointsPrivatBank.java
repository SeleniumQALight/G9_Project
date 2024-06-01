package apiPrivatBank;

public interface EndPointsPrivatBank {
    String BASE_URL = "https://api.privatbank.ua";
    String EXCHANGE_RATES = BASE_URL + "/p24api/exchange_rates";
    String CURRENCY_EXCHANGE = BASE_URL + "/p24api/pubinfo?json&exchange&coursid=5";
}
