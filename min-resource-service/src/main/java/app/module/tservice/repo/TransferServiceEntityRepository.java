package app.module.tservice.repo;

import app.module.tservice.entity.TransferServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

@SuppressWarnings("unused")
public interface TransferServiceEntityRepository extends MongoRepository<TransferServiceEntity, String> {
    Page<TransferServiceEntity> findAll(Pageable pageable);
    TransferServiceEntity findById(String id);
}
