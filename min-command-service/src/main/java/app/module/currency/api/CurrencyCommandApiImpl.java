package app.module.currency.api;

import app.module.currency.command.CreateCurrencyCommand;
import app.module.currency.command.DeleteCurrencyCommand;
import app.module.currency.command.UpdateCurrencyCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CurrencyCommandApiImpl implements CurrencyCommandApi {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandGateway commandGateway;
    public CurrencyCommandApiImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createCurrency(CreateCurrencyRequest request) {
        CompletableFuture<String> future = commandGateway.sendAndWait(CreateCurrencyCommand.from(newId(), request));

        return future
                .thenApply(s -> s)
                .exceptionally(e -> {
                    logger.warn("Create a currency failed with request: {}", request);
                    throw new RuntimeException(request.toString(), e);
                });
    }

    @Override
    public CompletableFuture<String> updateCurrency(String id, UpdateCurrencyRequest request) {
        CompletableFuture<String> future = commandGateway.sendAndWait(UpdateCurrencyCommand.from(id, request));

        return future
                .thenApply(s -> s)
                .exceptionally(e -> {
                    logger.warn("Update the currency{} failed with request: {}", id, request);
                    throw new RuntimeException(request.toString(), e);
                });
    }

    @Override
    public CompletableFuture<Void> deleteCurrency(@PathVariable String id) {
        return commandGateway.sendAndWait(DeleteCurrencyCommand.from(id));
    }

    private String newId(){
        return UUID.randomUUID().toString();
    }
}
