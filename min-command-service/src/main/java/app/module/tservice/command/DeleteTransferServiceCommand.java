package app.module.tservice.command;

import app.module.IdentifiableCommand;

public class DeleteTransferServiceCommand extends IdentifiableCommand<String, String> {
    public DeleteTransferServiceCommand(String s) {
        super(s);
    }


    public static DeleteTransferServiceCommand newCommand(String id) {
        return new DeleteTransferServiceCommand(id);
    }
}
