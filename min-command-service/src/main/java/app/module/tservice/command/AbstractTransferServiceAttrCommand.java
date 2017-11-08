package app.module.tservice.command;

import app.module.IdentifiableCommand;
import app.tservice.model.TransferService;

public class AbstractTransferServiceAttrCommand extends IdentifiableCommand<String, String>  {
    private final TransferService transferService;

    public AbstractTransferServiceAttrCommand(String id, TransferService transferService) {
        super(id);
        this.transferService = transferService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "transferService=" + transferService +
                '}';
    }
}
