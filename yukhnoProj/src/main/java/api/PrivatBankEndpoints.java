package api;

public interface PrivatBankEndpoints {
    String BASE_PB_URL = "https://api.privatbank.ua";
    String EXCHANGE_RATES_API = BASE_PB_URL + "/p24api/exchange_rates";

    String PUBLIC_EXCHANGE_API = BASE_PB_URL + "/p24api/pubinfo";
}
