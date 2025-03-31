package application.service;

import domain.ExchangeRate;
import java.util.List;

public interface ConvertCurrency {

    ExchangeRate getConversionRate(String baseCurrency, String targetCurrency);

    List<ExchangeRate> getAllConversionRate();
}
