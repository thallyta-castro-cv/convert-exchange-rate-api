package application.usecase;

import adapters.outbound.client.ExchangeRateClient;
import adapters.outbound.client.dto.ExchangeRateResponse;
import application.service.ConvertCurrency;
import domain.ConvertCurrencyRepository;
import domain.ExchangeRate;
import infraestructure.exceptions.CurrencyNotSupportedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ConvertCurrencyUseCases implements ConvertCurrency {

    String apiKey = "fb9f07fc148a077bacfa5b70";

    @Inject
    @RestClient
    private ExchangeRateClient exchangeRateClient;

    private final ConvertCurrencyRepository convertCurrencyRepository;

    public ConvertCurrencyUseCases(ConvertCurrencyRepository convertCurrencyRepository) {
        this.convertCurrencyRepository = convertCurrencyRepository;
    }

    @Transactional
    public ExchangeRate getConversionRate(String baseCurrency, String targetCurrency) {
        ExchangeRateResponse response = exchangeRateClient.getExchangeRate(apiKey, baseCurrency);

        Double conversionRate = Optional.ofNullable(response.getConversionRates().get(targetCurrency))
                .orElseThrow(() -> new CurrencyNotSupportedException(baseCurrency, targetCurrency));

        ExchangeRate exchangeRate = buildExchangeRate(baseCurrency, targetCurrency, conversionRate);
        convertCurrencyRepository.save(exchangeRate);
        return exchangeRate;
    }

    @Override
    public List<ExchangeRate> getAllConversionRate() {
        return convertCurrencyRepository.findAll();
    }

    private ExchangeRate buildExchangeRate(String baseCurrency, String targetCurrency, Double conversionRate) {
        return new ExchangeRate(baseCurrency, targetCurrency, conversionRate, LocalDateTime.now());
    }

}
