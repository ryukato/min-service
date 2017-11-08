package app.module.tservice.command;

import app.tservice.model.TransferService;

public class UpdateTransferServiceCommand extends AbstractTransferServiceAttrCommand {
    public UpdateTransferServiceCommand(String id, TransferService transferService) {
        super(id, transferService);
    }

    public static UpdateTransferServiceCommand newCommand(String id, TransferService transferService) {
        return new UpdateTransferServiceCommand(id, transferService);
    }
}
