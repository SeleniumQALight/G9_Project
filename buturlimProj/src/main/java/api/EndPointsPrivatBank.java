package api;

public interface EndPointsPrivatBank {
    String PRIVAT_URL = "https://api.privatbank.ua/";
    String EXCHANGE_RATES = PRIVAT_URL + "p24api/exchange_rates";
    String EXCHANGE_RATES_INFO = PRIVAT_URL + "p24api/pubinfo";
}
