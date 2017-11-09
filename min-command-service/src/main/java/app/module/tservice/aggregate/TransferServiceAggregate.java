package app.module.tservice.aggregate;

import app.module.tservice.command.AbstractTransferServiceAttrCommand;
import app.module.tservice.command.CreateTransferServiceCommand;
import app.module.tservice.command.DeleteTransferServiceCommand;
import app.module.tservice.command.UpdateTransferServiceCommand;
import app.module.tservice.event.TransferServiceCreatedEvent;
import app.module.tservice.event.TransferServiceRemovedEvent;
import app.module.tservice.event.TransferServiceUpdatedEvent;
import app.tservice.model.TransferService;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.io.Serializable;

@Aggregate
public class TransferServiceAggregate implements Serializable {

    @AggregateIdentifier
    private String id;
    private TransferService transferService;

    public TransferServiceAggregate() {
    }

    @CommandHandler
    public TransferServiceAggregate(CreateTransferServiceCommand command) {
        validateRequiredAttr(command);
        AggregateLifecycle.apply(new TransferServiceCreatedEvent(command.getId(), command.getTransferService()));
    }

    @EventSourcingHandler
    protected void on(TransferServiceCreatedEvent event) {
        this.id = event.getId();
        this.transferService = event.getTransferService();
    }

    @CommandHandler
    public void handle(UpdateTransferServiceCommand command) {
        validateRequiredAttr(command);
        AggregateLifecycle.apply(new TransferServiceUpdatedEvent(command.getId(), command.getTransferService()));
    }

    @EventSourcingHandler
    protected void on(TransferServiceUpdatedEvent event) {
        this.id = event.getId();
        this.transferService = event.getTransferService();
    }

    @CommandHandler
    public void handle(DeleteTransferServiceCommand command) {
        Assert.hasLength(command.getId(), "Missing id");
        AggregateLifecycle.apply(new TransferServiceRemovedEvent(command.getId()));
    }

    @EventSourcingHandler
    protected void on(TransferServiceRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

    private void validateRequiredAttr(AbstractTransferServiceAttrCommand command) {
        Assert.hasLength(command.getId(), "Missing id");
        Assert.hasLength(command.getTransferService().getName(), "Missing Transfer service name");
    }


}
