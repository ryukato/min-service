package app.module.country.command;

import app.country.event.CountryCreatedEvent;
import app.country.event.CountryRemovedEvent;
import app.country.event.CountryUpdatedEvent;
import app.country.model.Country;
import app.module.country.aggregate.CountryAggregate;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class CountryCommandHandlerTest {
    private FixtureConfiguration<CountryAggregate> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(CountryAggregate.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Test
    public void testCreateCountry()  throws Exception {
        Country country = Country.builder().withIsoCode("KOR").withName("Korea").withCurrencies(Arrays.asList("KOR")).build();

        fixture.givenNoPriorActivity()
                .when(CreateCountryCommand.newCommand(country))
                .expectEvents(new CountryCreatedEvent(country));
    }

    @Test
    public void testUpdateCountry()  throws Exception {
        String updateName = "Korea_update";
        Country country = Country.builder().withIsoCode("KOR").withName("Korea").withCurrencies(Arrays.asList("KOR")).build();
        Country updateCountry = Country.builder().withIsoCode("KOR").withName(updateName).withCurrencies(Arrays.asList("KOR")).build();

        fixture.given(new CountryCreatedEvent(country))
                .when(UpdateCountryCommand.newCommand(updateCountry))
                .expectEvents(new CountryUpdatedEvent(updateCountry));

    }

    @Test
    public void testDeleteCountry()  throws Exception {
        Country country = Country.builder().withIsoCode("KOR").withName("Korea").withCurrencies(Arrays.asList("KOR")).build();

        fixture.given(new CountryCreatedEvent(country))
                .when(DeleteCountryCommand.from(country.getIsoCode()))
                .expectEvents(new CountryRemovedEvent(country.getIsoCode()));
    }


}
