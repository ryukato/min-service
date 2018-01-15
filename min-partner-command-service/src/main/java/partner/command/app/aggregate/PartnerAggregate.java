package partner.command.app.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;
import partner.command.app.command.CreatePartnerCommand;
import partner.common.event.PartnerCreatedEvent;
import partner.common.model.Partner;

import java.io.Serializable;

@Aggregate
public class PartnerAggregate implements Serializable {
    @AggregateIdentifier
    private String id;
    private Partner partner;

    public PartnerAggregate() {
    }

    @CommandHandler
    public PartnerAggregate(CreatePartnerCommand command) {
        validateRequiredAttr(command);
        AggregateLifecycle.apply(new PartnerCreatedEvent(command.getId(), command.getPartner()));
    }

    @EventSourcingHandler
    public void handle(PartnerCreatedEvent event) {
        this.id = event.getId();
        this.partner = event.getPartner();
    }

    private void validateRequiredAttr(CreatePartnerCommand command) {
        Assert.hasLength(command.getId(), "Missing id");
        Assert.hasLength(command.getPartner().getName(), "Missing Partner name");
    }
}
