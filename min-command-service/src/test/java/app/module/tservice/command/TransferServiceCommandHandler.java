package app.module.tservice.command;

import app.module.tservice.aggregate.TransferServiceAggregate;
import app.module.tservice.event.TransferServiceCreatedEvent;
import app.tservice.model.TransferService;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TransferServiceCommandHandler {
    private FixtureConfiguration<TransferServiceAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(TransferServiceAggregate.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor());
    }

    @Test
    public void testCreateTransferService() throws Exception {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder().withName("test transfer-service").build();
        fixture.givenNoPriorActivity()
                .when(CreateTransferServiceCommand.newCommand(id, transferService))
                .expectEvents(new TransferServiceCreatedEvent(id, transferService));
    }

}
