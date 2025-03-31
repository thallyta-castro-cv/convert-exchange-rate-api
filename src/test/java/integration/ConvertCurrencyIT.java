package integration;

import adapters.outbound.client.ExchangeRateClient;
import adapters.outbound.client.dto.ExchangeRateResponse;
import application.usecase.ConvertCurrencyUseCases;
import domain.ConvertCurrencyRepository;
import domain.ExchangeRate;
import integration.config.CockroachDBTestResource;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@QuarkusTest
@QuarkusTestResource(value = CockroachDBTestResource.class, restrictToAnnotatedClass = true)
class ConvertCurrencyIT {

    @Inject
    ConvertCurrencyUseCases convertCurrencyUseCases;

    @Inject
    ConvertCurrencyRepository convertCurrencyRepository;

    @InjectMock
    @RestClient
    ExchangeRateClient exchangeRateClient;

    @Test
    void testGetConversionRateAndSaveToDatabase() {
        when(exchangeRateClient.getExchangeRate(anyString(), eq("USD")))
                .thenReturn(new ExchangeRateResponse(Map.of("EUR", 0.85)));

        convertCurrencyUseCases.getConversionRate("USD", "EUR");

        List<ExchangeRate> savedRates = convertCurrencyRepository.findAll();
        assertEquals("USD", savedRates.getFirst().getBaseCurrency());
        assertEquals("EUR", savedRates.getFirst().getTargetCurrency());

        verify(exchangeRateClient, times(1)).getExchangeRate(anyString(), eq("USD"));
    }

    @Test
    void testGetAllConversionRates() {
        convertCurrencyRepository.save(new ExchangeRate("USD", "EUR", 0.85, null));

        List<ExchangeRate> allRates = convertCurrencyUseCases.getAllConversionRate();

        assertEquals(1, allRates.size());
        assertEquals("USD", allRates.getFirst().getBaseCurrency());
        assertEquals("EUR", allRates.getFirst().getTargetCurrency());
    }
}
