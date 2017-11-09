package app.module.tservice.event;

import app.events.AbstractEvent;

public class TransferServiceRemovedEvent extends AbstractEvent {
    public TransferServiceRemovedEvent(String id) {
        super(id);
    }
}
