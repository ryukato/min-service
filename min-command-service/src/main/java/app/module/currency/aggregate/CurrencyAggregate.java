package app.module.currency.aggregate;

import app.currency.event.CurrencyCreatedEvent;
import app.currency.event.CurrencyRemovedEvent;
import app.currency.event.CurrencyUpdatedEvent;
import app.currency.model.Currency;
import app.module.currency.command.AbstractCurrencyAttrCommand;
import app.module.currency.command.CreateCurrencyCommand;
import app.module.currency.command.DeleteCurrencyCommand;
import app.module.currency.command.UpdateCurrencyCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@SuppressWarnings("unused")
@Aggregate
public class CurrencyAggregate implements Serializable {

    @AggregateIdentifier
    private String id;
    private Currency currency;

    public CurrencyAggregate() {
    }

    @CommandHandler
    public CurrencyAggregate(CreateCurrencyCommand command) {
        validateRequiredAttr(command);
        AggregateLifecycle.apply( new CurrencyCreatedEvent(command.getId(), command.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(CurrencyCreatedEvent event) {
        this.id = event.getId();
        this.currency = event.getCurrency();
    }

    @CommandHandler
    public void handle(UpdateCurrencyCommand command) {
        validateRequiredAttr(command);
        AggregateLifecycle.apply( new CurrencyUpdatedEvent(command.getId(), command.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(CurrencyUpdatedEvent event) {
        this.id = event.getId();
        this.currency = event.getCurrency();
    }

    @CommandHandler
    public void handle(DeleteCurrencyCommand command) {
        Assert.hasLength(command.identifier(), "Missing id");
        AggregateLifecycle.apply(new CurrencyRemovedEvent(command.identifier()));

    }

    @EventSourcingHandler
    public void on(CurrencyRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

    private void validateRequiredAttr(AbstractCurrencyAttrCommand command) {
        Assert.hasLength(command.getId(), "Missing id");
        Assert.hasLength(command.getCurrency().getCurrency(), "Missing currency");
        // TODO: validate more attributes of currency
    }
}
