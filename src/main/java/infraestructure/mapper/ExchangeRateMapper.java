package infraestructure.mapper;

import adapters.inbound.entity.ExchangeRateEntity;
import domain.ExchangeRate;

public class ExchangeRateMapper {

    public static ExchangeRateEntity toEntity(ExchangeRate domain) {
        ExchangeRateEntity entity = new ExchangeRateEntity();
        entity.setBaseCurrency(domain.getBaseCurrency());
        entity.setTargetCurrency(domain.getTargetCurrency());
        entity.setRate(domain.getRate());
        entity.setTimestamp(domain.getTimestamp());

        return entity;
    }

    public static ExchangeRate toDomain(ExchangeRateEntity entity) {

        return new ExchangeRate(
                entity.getBaseCurrency(),
                entity.getTargetCurrency(),
                entity.getRate(),
                entity.getTimestamp()
        );
    }
}
