package app.module.currency.handler;

import app.currency.event.CurrencyCreatedEvent;
import app.currency.event.CurrencyRemovedEvent;
import app.currency.event.CurrencyUpdatedEvent;
import app.module.currency.entity.CurrencyEntity;
import app.module.currency.repo.CurrencyRepository;
import org.axonframework.eventhandling.EventHandler;

import java.util.Optional;

public class CurrencyEventHandler {
    private final CurrencyRepository repository;
    public CurrencyEventHandler(CurrencyRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CurrencyCreatedEvent event) {
        this.repository.save(CurrencyEntity.from(event));
    }

    @EventHandler
    public void on(CurrencyUpdatedEvent event) {
        CurrencyEntity currencyEntity = this.repository.findById(event.getId());
        Optional.ofNullable(currencyEntity).ifPresent(c -> this.repository.save(CurrencyEntity.from(event)));

    }

    @EventHandler
    public void on(CurrencyRemovedEvent event) {
        CurrencyEntity currencyEntity = this.repository.findById(event.getId());
        Optional.ofNullable(currencyEntity).ifPresent(c -> {
            c.setActive(false);
            repository.save(c);
        });
    }
}
