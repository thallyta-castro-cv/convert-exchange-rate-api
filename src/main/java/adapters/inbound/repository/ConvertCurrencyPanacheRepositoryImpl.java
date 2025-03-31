package adapters.inbound.repository;

import adapters.inbound.entity.ExchangeRateEntity;
import domain.ConvertCurrencyRepository;
import domain.ExchangeRate;
import infraestructure.mapper.ExchangeRateMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ConvertCurrencyPanacheRepositoryImpl implements ConvertCurrencyRepository {

    @Inject
    ConvertCurrencyPanacheRepository convertCurrencyPanacheRepository;

    public ConvertCurrencyPanacheRepositoryImpl(ConvertCurrencyPanacheRepository convertCurrencyPanacheRepository) {
        this.convertCurrencyPanacheRepository = convertCurrencyPanacheRepository;
    }

    @Override
    @Transactional
    public void save(ExchangeRate exchangeRate) {
        ExchangeRateEntity exchangeRateEntity = ExchangeRateMapper.toEntity(exchangeRate);
        this.convertCurrencyPanacheRepository.persist(exchangeRateEntity);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return convertCurrencyPanacheRepository.findAll()
                .list()
                .stream()
                .map(ExchangeRateMapper::toDomain)
                .toList();
    }
}
