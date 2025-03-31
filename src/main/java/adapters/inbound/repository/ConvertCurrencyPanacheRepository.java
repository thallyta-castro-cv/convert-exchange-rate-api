package adapters.inbound.repository;

import adapters.inbound.entity.ExchangeRateEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConvertCurrencyPanacheRepository implements PanacheRepository<ExchangeRateEntity> {
}
