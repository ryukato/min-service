package app.module.country.handler;

import app.country.event.CountryCreatedEvent;
import app.country.event.CountryRemovedEvent;
import app.country.event.CountryUpdatedEvent;
import app.country.model.Country;
import app.module.country.entity.CountryEntity;
import app.module.country.repo.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CountryEventHandlerTest {

    @Mock
    private CountryRepository repository;

    private CountryEventHandler countryEventHandler;

    @Before
    public void setUp() {
        repository = Mockito.mock(CountryRepository.class);
        countryEventHandler = new CountryEventHandler(repository);
    }

    @Test
    public void testHandleCountryCreatedEvent() {
        Country country = Country.builder()
                .withIsoCode("KOR")
                .withName("Korea")
                .build();
        CountryCreatedEvent event = new CountryCreatedEvent(country);
        this.countryEventHandler.on(event);
        verify(repository, times(1)).save(any(CountryEntity.class));
        verify(repository, times(1)).save(new CountryEntity(country.getIsoCode(), country));
    }

    @Test
    public void testHandleCountryUpdatedEvent() {

        Country country = Country.builder()
                .withIsoCode("KOR")
                .withName("Korea")
                .build();

        Country updatedCountry = Country.builder()
                .withIsoCode("KOR")
                .withName("Korea_updated")
                .build();

        CountryUpdatedEvent event = new CountryUpdatedEvent(updatedCountry);

        when(repository.findById(event.getId())).thenReturn(new CountryEntity(event.getId(), country));

        this.countryEventHandler.on(event);
        verify(repository, times(1)).save(any(CountryEntity.class));
        verify(repository, times(1)).save(new CountryEntity(updatedCountry.getIsoCode(), updatedCountry));
    }

    @Test
    public void testHandleCountryUpdatedEvent_no_saved_country() {
         Country updatedCountry = Country.builder()
                .withIsoCode("KOR")
                .withName("Korea_updated")
                .build();

        CountryUpdatedEvent event = new CountryUpdatedEvent(updatedCountry);

        when(repository.findById(event.getId())).thenReturn(null);

        this.countryEventHandler.on(event);
        verify(repository, times(0)).save(any(CountryEntity.class));
    }

    @Test
    public void testHandleCountryRemovedEvent() {
        Country country = Country.builder()
                .withIsoCode("KOR")
                .withName("Korea")
                .build();
        CountryRemovedEvent event = new CountryRemovedEvent(country.getIsoCode());

        when(repository.findById(event.getId())).thenReturn(new CountryEntity(event.getId(), country));

        CountryEntity countryEntity = new CountryEntity(country.getIsoCode(), country);
        countryEntity.setActive(false);
        this.countryEventHandler.on(event);
        verify(repository, times(1)).save(any(CountryEntity.class));
        verify(repository, times(1)).save(countryEntity);
    }

    @Test
    public void testHandleCountryRemovedEvent_no_saved_country() {

        CountryRemovedEvent event = new CountryRemovedEvent("not_exist_country");

        when(repository.findById(event.getId())).thenReturn(null);

        this.countryEventHandler.on(event);
        verify(repository, times(0)).save(any(CountryEntity.class));
    }
}
