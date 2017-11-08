package app.module.country.api;

import app.country.model.Country;
import app.module.country.api.impl.CountryCommandApiImpl;
import app.module.country.command.CreateCountryCommand;
import app.module.country.command.DeleteCountryCommand;
import app.module.country.command.UpdateCountryCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CountryCommandApiTest {
    @MockBean
    private CommandGateway commandGateway;


    CountryCommandApi countryCommandApi;

    @Before
    public void setUp() {
        countryCommandApi = new CountryCommandApiImpl(commandGateway);
    }

    @Test
    public void testCreateCountry() throws Exception {
        Country country = Country.builder().withIsoCode("KOR").withName("Korea").withCurrencies(Arrays.asList("KR")).build();
        CreateCountryRequest request = new CreateCountryRequest(country);
        CreateCountryCommand command = CreateCountryCommand.from(request);
        // Arrange
        when(commandGateway.sendAndWait(command))
                .thenAnswer(i -> {
                    CompletableFuture<String> response = new CompletableFuture<>();
                    response.complete(command.getCountry().getIsoCode());
                    return response;
                });

        // Act
        CompletableFuture<String> answer = countryCommandApi.createCountry(request);

        // verify
        verify(commandGateway, times(1)).sendAndWait(command);
        verifyNoMoreInteractions(commandGateway);
        assertEquals(request.getCountry().getIsoCode(), answer.get().toString());
    }

    @Test
    public void testUpdateCountry() throws Exception {
        Country country = Country.builder().withIsoCode("KOR").withName("Korea").withCurrencies(Arrays.asList("KR")).build();
        UpdateCountryRequest request = new UpdateCountryRequest(country);

        UpdateCountryCommand command = UpdateCountryCommand.from(request);
        // Arrange
        when(commandGateway.sendAndWait(command))
                .thenAnswer(i -> {
                    CompletableFuture<String> response = new CompletableFuture<>();
                    response.complete(command.getCountry().getIsoCode());
                    return response;
                });

        // Act
        CompletableFuture<String> answer = countryCommandApi.updateCountry(request.getCountry().getIsoCode(), request);

        // verify
        verify(commandGateway, times(1)).sendAndWait(command);
        verifyNoMoreInteractions(commandGateway);
        assertEquals(request.getCountry().getIsoCode(), answer.get().toString());
    }

    @Test
    public void testDeleteCountry() throws Exception {
        String isoCode = "KOR";

        DeleteCountryCommand command = DeleteCountryCommand.from(isoCode);
        // Arrange
        when(commandGateway.sendAndWait(command))
                .thenAnswer(i -> {
                    CompletableFuture<Void> response = new CompletableFuture<>();
                    response.complete(null);
                    return response;
                });

        // Act
        countryCommandApi.deleteCountry(isoCode);

        // verify
        verify(commandGateway, times(1)).sendAndWait(command);
        verifyNoMoreInteractions(commandGateway);
    }
}
