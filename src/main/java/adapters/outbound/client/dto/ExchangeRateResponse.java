package adapters.outbound.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class ExchangeRateResponse {

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;

    public ExchangeRateResponse(){}

    public ExchangeRateResponse(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}

