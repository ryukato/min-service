package app.module.currency.api;

import app.currency.model.Currency;
import app.module.currency.command.CreateCurrencyCommand;
import app.module.currency.command.DeleteCurrencyCommand;
import app.module.currency.command.UpdateCurrencyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CurrencyCommandApiTest {
    @MockBean
    CommandGateway commandGateway;

    CurrencyCommandApi currencyCommandApi;

    @Before
    public void setUp() throws Exception {
        currencyCommandApi = new CurrencyCommandApiImpl(commandGateway);
    }

    @Test
    public void testCreateCurrency() throws Exception {
        String id = UUID.randomUUID().toString();
        CreateCurrencyRequest request = createCreateCurrencyRequest();
        CreateCurrencyCommand command = CreateCurrencyCommand.from(id, request);

        // Arrange
        when(commandGateway.sendAndWait(any(CreateCurrencyCommand.class)))
                .thenAnswer(i -> {
                    CompletableFuture<String> response = new CompletableFuture<>();
                    response.complete(id);
                    return response;
                });

        // Act
        CompletableFuture<String> answer = currencyCommandApi.createCurrency(request);

        // verify
        verify(commandGateway, times(1)).sendAndWait(any(CreateCurrencyCommand.class));
        verifyNoMoreInteractions(commandGateway);
        assertEquals(command.getId(), answer.get().toString());

    }

    @Test
    public void testUpdateCurrency() throws Exception {
        String id = UUID.randomUUID().toString();
        UpdateCurrencyRequest request = createUpdateCurrencyRequest();

        UpdateCurrencyCommand command = UpdateCurrencyCommand.from(id, request);

        // Arrange
        when(commandGateway.sendAndWait(any(UpdateCurrencyCommand.class)))
                .thenAnswer(i -> {
                    CompletableFuture<String> response = new CompletableFuture<>();
                    response.complete(id);
                    return response;
                });

        // Act
        CompletableFuture<String> answer = currencyCommandApi.updateCurrency(id, request);
        // verify
        verify(commandGateway, times(1)).sendAndWait(any(UpdateCurrencyCommand.class));
        verifyNoMoreInteractions(commandGateway);
        assertEquals(command.getId(), answer.get().toString());
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        String id = UUID.randomUUID().toString();
        DeleteCurrencyCommand command = DeleteCurrencyCommand.from(id);

        // Arrange
        when(commandGateway.sendAndWait(command))
                .thenAnswer(i -> {
                    CompletableFuture<Void> response = new CompletableFuture<>();
                    response.complete(null);
                    return response;
                });

        // Act
        currencyCommandApi.deleteCurrency(id);
        // verify
        verify(commandGateway, times(1)).sendAndWait(command);
        verifyNoMoreInteractions(commandGateway);
    }
    
    
    private CreateCurrencyRequest createCreateCurrencyRequest() {
        Currency currency = createTestCurrency();
        return new CreateCurrencyRequest(currency);
    }

    private UpdateCurrencyRequest createUpdateCurrencyRequest() {
        Currency currency = createTestCurrency();
        return new UpdateCurrencyRequest(currency);
    }



    private Currency createTestCurrency() {
        return Currency.builder()
                    .withBaseDateTime(LocalDateTime.now())
                    .withCurrencyInKorean("미국")
                    .withCurrency("USD")
                    .withBuyInCashCurrency(BigDecimal.ONE)
                    .withBuyInCashSpread(BigDecimal.ONE)
                    .withSellInCashCurrency(BigDecimal.ONE)
                    .withSellInCashSpread(BigDecimal.ONE)
                    .withBuyInWireCurrency(BigDecimal.ONE)
                    .withSellInWireCurrency(BigDecimal.ONE)
                    .withTravelerCheckCurrency(BigDecimal.ONE)
                    .withForeignCheckCurrency(BigDecimal.ONE)
                    .withSellingBaseRate(BigDecimal.ONE)
                    .withCurrencyInDollar(BigDecimal.ONE).build();
    }
}
