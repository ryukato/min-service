package app.module.tservice.command;

import app.tservice.model.TransferService;

public class CreateTransferServiceCommand extends AbstractTransferServiceAttrCommand {
    public CreateTransferServiceCommand(String id, TransferService transferService) {
        super(id, transferService);
    }

    public static CreateTransferServiceCommand newCommand(String id, TransferService transferService) {
        return new CreateTransferServiceCommand(id, transferService);
    }
}
