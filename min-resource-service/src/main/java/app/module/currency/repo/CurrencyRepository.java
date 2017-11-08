package app.module.currency.repo;

import app.module.currency.entity.CurrencyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unused")
public interface CurrencyRepository extends MongoRepository<CurrencyEntity, String> {
    Page<CurrencyEntity> findAll(Pageable pageable);

    CurrencyEntity findById(String id);

    @Query("{'currency': {$eq: ?0}}")
    CurrencyEntity findByName(@Param("name") String name);

    @Query("{'currencyInKorean': {$eq: ?0}}")
    CurrencyEntity findByKoreanName(@Param("name") String name);
}
