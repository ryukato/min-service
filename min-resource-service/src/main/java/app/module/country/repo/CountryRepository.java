package app.module.country.repo;

import app.module.country.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unused")
public interface CountryRepository extends MongoRepository<CountryEntity, String> {
    Page<CountryEntity> findAll(Pageable pageable);
    CountryEntity findById(String id);

    @Query("{'name': {$eq: ?0}}")
    CountryEntity findByName(@Param("name") String name);

    @Query("{'name': {$regex: ?0, $options: 'xsi'}}")
    Page<CountryEntity> findByNameLike(@Param("name") String name, Pageable pageable);

    @Query("{'iso': {$eq: ?0}}")
    CountryEntity findByIso(@Param("iso") String iso);
}
