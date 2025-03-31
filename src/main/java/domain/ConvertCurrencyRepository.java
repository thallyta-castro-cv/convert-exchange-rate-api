package domain;

import java.util.List;

public interface ConvertCurrencyRepository {
    void save(ExchangeRate exchangeRate);
    List<ExchangeRate> findAll();
}
