package app.module.tservice.command;

import app.module.tservice.aggregate.TransferServiceAggregate;
import app.tservice.event.TransferServiceCreatedEvent;
import app.tservice.event.TransferServiceRemovedEvent;
import app.tservice.event.TransferServiceUpdatedEvent;
import app.tservice.model.TransferService;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TransferServiceCommandHandlerTest {
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

    @Test
    public void testUpdateTransferService() throws Exception {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder().withName("test transfer-service").build();
        TransferService updateTransferService = TransferService.builder().withName("new transfer-service").build();

        fixture.given(new TransferServiceCreatedEvent(id, transferService))
                .when(UpdateTransferServiceCommand.newCommand(id, updateTransferService))
                .expectEvents(new TransferServiceUpdatedEvent(id, updateTransferService));
    }

    @Test
    public void testDeleteTransferService() throws Exception {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder().withName("test transfer-service").build();

        fixture.given(new TransferServiceCreatedEvent(id, transferService))
                .when(DeleteTransferServiceCommand.newCommand(id))
                .expectEvents(new TransferServiceRemovedEvent(id));
    }

}
