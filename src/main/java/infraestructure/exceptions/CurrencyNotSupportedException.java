package infraestructure.exceptions;

public class CurrencyNotSupportedException extends RuntimeException {

    public CurrencyNotSupportedException(String baseCurrency, String targetCurrency) {
        super("Conversion rate not available for currency pair: " + baseCurrency + " to " + targetCurrency);
    }
}