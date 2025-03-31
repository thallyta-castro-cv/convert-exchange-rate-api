package domain;

import java.time.LocalDateTime;

public class ExchangeRate {
    public String baseCurrency;
    public String targetCurrency;
    public double rate;
    public LocalDateTime timestamp;

    public ExchangeRate(String baseCurrency, String targetCurrency, Double rate, LocalDateTime timestamp) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getRate() {
        return rate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
