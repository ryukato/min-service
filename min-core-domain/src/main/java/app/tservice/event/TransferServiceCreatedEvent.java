package app.tservice.event;

import app.events.AbstractEvent;
import app.tservice.model.TransferService;

public class TransferServiceCreatedEvent extends AbstractEvent {
    private final TransferService transferService;
    public TransferServiceCreatedEvent(String id, TransferService transferService) {
        super(id);
        this.transferService = transferService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    @Override
    public String toString() {
        return "TransferServiceCreatedEvent{ transferService=" + transferService + " }";
    }
}
