package app.module.currency.handler;

import app.currency.event.CurrencyCreatedEvent;
import app.currency.event.CurrencyRemovedEvent;
import app.currency.event.CurrencyUpdatedEvent;
import app.currency.model.Currency;
import app.module.country.entity.CountryEntity;
import app.module.currency.entity.CurrencyEntity;
import app.module.currency.repo.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrencyEventHandlerTest {

    @Mock
    private CurrencyRepository repository;

    private CurrencyEventHandler currencyEventHandler;

    @Before
    public void setUp() {
        repository = Mockito.mock(CurrencyRepository.class);
        this.currencyEventHandler = new CurrencyEventHandler(repository);
    }

    @Test
    public void testHandleCurrencyCreatedEvent() {
        String id = UUID.randomUUID().toString();
        Currency currency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("원")
                .withBaseDateTime(LocalDateTime.now()).build();

        CurrencyCreatedEvent event = new CurrencyCreatedEvent(id, currency);
        this.currencyEventHandler.on(event);
        verify(repository, times(1)).save(any(CurrencyEntity.class));
        verify(repository, times(1)).save(new CurrencyEntity(id, currency));
    }

    @Test
    public void testHandleCurrencyUpdatedEvent() {
        String id = UUID.randomUUID().toString();
        Currency currency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("원")
                .withBaseDateTime(LocalDateTime.now()).build();

        Currency updateCurrency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("대한민국 원 ")
                .withBaseDateTime(LocalDateTime.now()).build();

        CurrencyUpdatedEvent event = new CurrencyUpdatedEvent(id, updateCurrency);

        when(repository.findById(event.getId())).thenReturn(new CurrencyEntity(event.getId(), currency));

        this.currencyEventHandler.on(event);
        verify(repository, times(1)).save(any(CurrencyEntity.class));
        verify(repository, times(1)).save(new CurrencyEntity(id, updateCurrency));
    }

    @Test
    public void testHandleCurrencyUpdatedEvent_no_saved_country() {
        String id = UUID.randomUUID().toString();
        Currency updateCurrency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("대한민국 원 ")
                .withBaseDateTime(LocalDateTime.now()).build();

        CurrencyUpdatedEvent event = new CurrencyUpdatedEvent(id, updateCurrency);

        when(repository.findById(event.getId())).thenReturn(null);

        this.currencyEventHandler.on(event);
        verify(repository, times(0)).save(any(CurrencyEntity.class));
    }

    @Test
    public void testHandleCurrencyRemovedEvent() {
        String id = UUID.randomUUID().toString();
        Currency currency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("원")
                .withBaseDateTime(LocalDateTime.now()).build();

        CurrencyRemovedEvent event = new CurrencyRemovedEvent(id);

        CurrencyEntity currencyEntity = new CurrencyEntity(event.getId(), currency);
        currencyEntity.setActive(false);
        when(repository.findById(event.getId())).thenReturn(currencyEntity);

        this.currencyEventHandler.on(event);
        verify(repository, times(1)).save(any(CurrencyEntity.class));
        verify(repository, times(1)).save(currencyEntity);
    }

    @Test
    public void testHandleCurrencyRemovedEvent_no_saved_country() {
        String id = UUID.randomUUID().toString();
        Currency updateCurrency = Currency.builder()
                .withCurrency("KRW")
                .withCurrencyInKorean("대한민국 원 ")
                .withBaseDateTime(LocalDateTime.now()).build();

        CurrencyRemovedEvent event = new CurrencyRemovedEvent(id);

        when(repository.findById(event.getId())).thenReturn(null);

        this.currencyEventHandler.on(event);
        verify(repository, times(0)).save(any(CurrencyEntity.class));
    }
}
