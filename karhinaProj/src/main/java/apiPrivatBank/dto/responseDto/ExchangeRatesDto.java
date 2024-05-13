package apiPrivatBank.dto.responseDto;

import java.util.List;

public class ExchangeRatesDto  {


    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private List<CurrencyDto> exchangeRate;

    public ExchangeRatesDto() {
    }

    public ExchangeRatesDto (String date, String bank, Integer baseCurrency, String baseCurrencyLit,  List<CurrencyDto> exchangeRate) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRate = exchangeRate;
    }

    // Геттеры и сеттеры для всех полей

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public List<CurrencyDto> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<CurrencyDto> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRatesDto{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }

}
