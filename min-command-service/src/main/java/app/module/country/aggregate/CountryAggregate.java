package app.module.country.aggregate;

import app.country.event.CountryCreatedEvent;
import app.country.event.CountryRemovedEvent;
import app.country.event.CountryUpdatedEvent;
import app.country.model.Country;
import app.module.country.command.CreateCountryCommand;
import app.module.country.command.DeleteCountryCommand;
import app.module.country.command.UpdateCountryCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@SuppressWarnings("unused")
@Aggregate
public class CountryAggregate implements Serializable {

    @AggregateIdentifier
    private String isoCode;

    private Country country;

    public CountryAggregate() {
    }

    @CommandHandler
    public CountryAggregate(CreateCountryCommand command) {
        AggregateLifecycle.apply(new CountryCreatedEvent(command.getCountry()));
    }

    @EventSourcingHandler
    protected void on(CountryCreatedEvent event) {
        this.isoCode = event.getCountry().getIsoCode();
        this.country = event.getCountry();
    }

    @CommandHandler
    public void handle(UpdateCountryCommand command) {
        AggregateLifecycle.apply(new CountryUpdatedEvent(command.getCountry()));
    }

    @EventSourcingHandler
    protected void on(CountryUpdatedEvent event) {
        this.country = event.getCountry();
    }

    @CommandHandler
    public void handle(DeleteCountryCommand command) {
        Assert.hasLength(command.identifier(), "Missing id");
        AggregateLifecycle.apply(new CountryRemovedEvent(command.identifier()));

    }

    @EventSourcingHandler
    public void on(CountryRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
