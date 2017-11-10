package app.module.tservice.handler;

import app.module.currency.entity.CurrencyEntity;
import app.module.tservice.entity.TransferServiceEntity;
import app.module.tservice.repo.TransferServiceEntityRepository;
import app.tservice.event.TransferServiceCreatedEvent;
import app.tservice.event.TransferServiceRemovedEvent;
import app.tservice.event.TransferServiceUpdatedEvent;
import app.tservice.model.TransferService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferServiceEventHandlerTest {

    @Mock
    private TransferServiceEntityRepository repository;

    private TransferServiceEventHandler transferServiceEventHandler;

    @Before
    public void setUp() {
        repository = Mockito.mock(TransferServiceEntityRepository.class);
        transferServiceEventHandler = new TransferServiceEventHandler(repository);
    }

    @Test
    public void testHandleTransferServiceCreatedEvent() {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder()
                .withName("test transfer service")
                .build();

        TransferServiceCreatedEvent event = new TransferServiceCreatedEvent(id, transferService);
        this.transferServiceEventHandler.on(event);

        verify(repository, times(1)).save(any(TransferServiceEntity.class));
        verify(repository, times(1)).save(new TransferServiceEntity(id, transferService));
    }

    @Test
    public void testHandleTransferServiceUpdatedEvent() {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder()
                .withName("test transfer service")
                .build();

        TransferService updateTransferService = TransferService.builder()
                .withName("updated transfer service")
                .build();

        TransferServiceUpdatedEvent event = new TransferServiceUpdatedEvent(id, updateTransferService);

        when(repository.findById(event.getId())).thenReturn(new TransferServiceEntity(event.getId(), transferService));

        this.transferServiceEventHandler.on(event);

        verify(repository, times(1)).save(any(TransferServiceEntity.class));
        verify(repository, times(1)).save(new TransferServiceEntity(id, updateTransferService));
    }

    @Test
    public void testHandleTransferServiceUpdatedEvent_no_saved_transferService() {
        String id = UUID.randomUUID().toString();

        TransferService updateTransferService = TransferService.builder()
                .withName("updated transfer service")
                .build();

        TransferServiceUpdatedEvent event = new TransferServiceUpdatedEvent(id, updateTransferService);

        when(repository.findById(event.getId())).thenReturn(null);

        this.transferServiceEventHandler.on(event);

        verify(repository, times(0)).save(any(TransferServiceEntity.class));
    }

    @Test
    public void testHandleTransferServiceRemovedEvent() {
        String id = UUID.randomUUID().toString();
        TransferService transferService = TransferService.builder()
                .withName("test transfer service")
                .build();

        TransferServiceRemovedEvent event = new TransferServiceRemovedEvent(id);

        TransferServiceEntity transferServiceEntity = new TransferServiceEntity(event.getId(), transferService);
        transferServiceEntity.setActive(false);
        when(repository.findById(event.getId())).thenReturn(transferServiceEntity);

        this.transferServiceEventHandler.on(event);

        verify(repository, times(1)).save(any(TransferServiceEntity.class));
    }

    @Test
    public void testHandleTransferServiceRemovedEvent_no_saved_transferService() {
        String id = UUID.randomUUID().toString();

        TransferServiceRemovedEvent event = new TransferServiceRemovedEvent(id);

        when(repository.findById(event.getId())).thenReturn(null);

        this.transferServiceEventHandler.on(event);

        verify(repository, times(0)).save(any(TransferServiceEntity.class));
    }

}
