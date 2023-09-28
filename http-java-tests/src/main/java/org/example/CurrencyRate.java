package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyRate {
    private String ccy;       // Currency
    private String base_ccy;  // Base currency
    private String buy;       // Buy rate
    private String sale;      // Sale rate
}
