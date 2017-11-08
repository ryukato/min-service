package app.module.currency.command;

import app.currency.event.CurrencyCreatedEvent;
import app.currency.event.CurrencyRemovedEvent;
import app.currency.event.CurrencyUpdatedEvent;
import app.currency.model.Currency;
import app.module.currency.aggregate.CurrencyAggregate;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CurrencyCommandHandlerTest {
    private FixtureConfiguration<CurrencyAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(CurrencyAggregate.class);
        fixture.registerCommandDispatchInterceptor(new BeanValidationInterceptor());
    }

    @Test
    public void testCreateCountry() throws Exception {
        String id = UUID.randomUUID().toString();
        Currency currency = createTestCurrency();
        fixture.givenNoPriorActivity()
                .when(CreateCurrencyCommand.newCommand(id, currency))
                .expectEvents(new CurrencyCreatedEvent(id, currency));
    }

    @Test
    public void testUpdateCountry() throws Exception {
        String id = UUID.randomUUID().toString();
        Currency currency = createTestCurrency();
        Currency updateCurrency = createTestCurrencyWithName("미국달러");
        fixture.given(new CurrencyCreatedEvent(id, currency))
                .when(UpdateCurrencyCommand.newCommand(id, updateCurrency))
                .expectEvents(new CurrencyUpdatedEvent(id, updateCurrency));
    }

    @Test
    public void testDeleteCountry() throws Exception {
        String id = UUID.randomUUID().toString();
        Currency currency = createTestCurrency();
        fixture.given(new CurrencyCreatedEvent(id, currency))
                .when(DeleteCurrencyCommand.from(id))
                .expectEvents(new CurrencyRemovedEvent(id));
    }

    private Currency createTestCurrency() {
        return createTestCurrencyWithName("미국");
    }

    private Currency createTestCurrencyWithName(String currencyInKorean) {
        return Currency.builder()
                .withBaseDateTime(LocalDateTime.now())
                .withCurrencyInKorean(currencyInKorean)
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
