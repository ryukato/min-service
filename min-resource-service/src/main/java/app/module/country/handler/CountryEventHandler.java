package app.module.country.handler;

import app.module.country.repo.CountryRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void on () {

    }
}
