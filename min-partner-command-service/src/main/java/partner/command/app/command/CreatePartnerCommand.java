package partner.command.app.command;

import partner.common.model.Partner;

public class CreatePartnerCommand {
    private final String id;
    private final Partner partner;

    public CreatePartnerCommand(String id, Partner partner) {
        this.id = id;
        this.partner = partner;
    }

    public static CreatePartnerCommand newCommand(String id, Partner partner) {
        return new CreatePartnerCommand(id, partner);
    }

    public String getId() {
        return id;
    }

    public Partner getPartner() {
        return partner;
    }
}
