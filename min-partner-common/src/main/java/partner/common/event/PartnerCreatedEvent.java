package partner.common.event;

import partner.common.model.Partner;

public class PartnerCreatedEvent {
    private final String id;
    private final Partner partner;
    public PartnerCreatedEvent(String id, Partner partner) {
        this.id = id;
        this.partner = partner;
    }

    public String getId() {
        return id;
    }

    public Partner getPartner() {
        return partner;
    }
}
