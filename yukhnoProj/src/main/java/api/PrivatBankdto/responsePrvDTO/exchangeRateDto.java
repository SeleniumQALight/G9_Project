package api.PrivatBankdto.responsePrvDTO;

public class exchangeRateDto {
    private String baseCurrency;
    private String currency;
    private Float saleRateNB;
    private Float purchaseRateNB;
    private Float saleRate;
    private Float purchaseRate;

    public exchangeRateDto(String baseCurrency, String currency) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
    }


    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "exchangeRateDto{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
