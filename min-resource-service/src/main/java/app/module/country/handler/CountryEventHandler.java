package app.module.country.handler;

import app.country.event.CountryCreatedEvent;
import app.country.event.CountryRemovedEvent;
import app.country.event.CountryUpdatedEvent;
import app.country.model.Country;
import app.module.country.entity.CountryEntity;
import app.module.country.repo.CountryRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("country-events")
public class CountryEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CountryRepository repository;

    @Autowired
    public CountryEventHandler(CountryRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(CountryCreatedEvent event) {
        repository.save(CountryEntity.from(event));
    }

    @EventHandler
    public void on(CountryUpdatedEvent event) {
        CountryEntity countryEntity = repository.findById(event.getId());
        Optional.ofNullable(countryEntity).ifPresent(c -> repository.save(CountryEntity.from(event)));
    }

    @EventHandler
    public void on(CountryRemovedEvent event) {
        CountryEntity countryEntity = repository.findById(event.getId());
        Optional.ofNullable(countryEntity).ifPresent(c -> {
            c.setActive(false);
            repository.save(c);
        });
    }
}
