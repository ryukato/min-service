package app.module.tservice.handler;

import app.module.tservice.entity.TransferServiceEntity;
import app.module.tservice.repo.TransferServiceEntityRepository;
import app.tservice.event.TransferServiceCreatedEvent;
import app.tservice.event.TransferServiceRemovedEvent;
import app.tservice.event.TransferServiceUpdatedEvent;

import java.util.Optional;

public class TransferServiceEventHandler {
    private final TransferServiceEntityRepository repository;
    public TransferServiceEventHandler(TransferServiceEntityRepository repository) {
        this.repository = repository;
    }

    public void on(TransferServiceCreatedEvent event) {
        repository.save(TransferServiceEntity.from(event));
    }

    public void on(TransferServiceUpdatedEvent event) {
        TransferServiceEntity entity = repository.findById(event.getId());
        Optional.ofNullable(entity).ifPresent(c -> repository.save(TransferServiceEntity.from(event)));
    }

    public void on(TransferServiceRemovedEvent event) {
        TransferServiceEntity entity = repository.findById(event.getId());
        Optional.ofNullable(entity).ifPresent(c -> {
            c.setActive(false);
            repository.save(c);
        });
    }
}
