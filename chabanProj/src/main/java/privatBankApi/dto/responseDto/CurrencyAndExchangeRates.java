package privatBankApi.dto.responseDto;

import io.cucumber.java.eo.Do;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CurrencyAndExchangeRates {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
