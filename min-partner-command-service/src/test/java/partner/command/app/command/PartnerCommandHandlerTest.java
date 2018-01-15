package partner.command.app.command;

import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import partner.command.app.aggregate.PartnerAggregate;
import partner.common.event.PartnerCreatedEvent;
import partner.common.model.Partner;

import java.util.UUID;

public class PartnerCommandHandlerTest {
    private FixtureConfiguration<PartnerAggregate> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(PartnerAggregate.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void testCreatePartner() throws Exception {
        String id = UUID.randomUUID().toString();
        Partner partner = Partner.builder().withName("test partner")
                .withEmail("test_partner@test.com")
                .withPassword("test password")
                .withHomePage("test homePage")
                .build();

        fixture.givenNoPriorActivity()
                .when(CreatePartnerCommand.newCommand(id, partner))
                .expectEvents(new PartnerCreatedEvent(id, partner));
    }

}
