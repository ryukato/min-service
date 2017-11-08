package app.module.country.api.impl;

import app.module.country.api.CountryCommandApi;
import app.module.country.api.CreateCountryRequest;
import app.module.country.api.UpdateCountryRequest;
import app.module.country.command.CreateCountryCommand;
import app.module.country.command.DeleteCountryCommand;
import app.module.country.command.UpdateCountryCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@RestController
public class CountryCommandApiImpl implements CountryCommandApi {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandGateway commandGateway;

    public CountryCommandApiImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createCountry(@RequestBody CreateCountryRequest request) {
        CompletableFuture<String> future = commandGateway.sendAndWait(CreateCountryCommand.from(request));

        return future
                .thenApply(s -> s)
                .exceptionally(e -> {
                    logger.warn("Create a country failed with request: {}", request);
                    throw new RuntimeException(request.toString(), e);
                });
    }

    @Override
    public CompletableFuture<String> updateCountry(@PathVariable String id, @RequestBody UpdateCountryRequest request) {
        CompletableFuture<String> future = commandGateway.sendAndWait(UpdateCountryCommand.from(request));

        return future
                .thenApply(s -> s)
                .exceptionally(e -> {
                    logger.warn("Update the country {} failed with request: {}", id,  request);
                    throw new RuntimeException("Update the country failed with request: " + request.toString(), e);
                });
    }

    @Override
    public CompletableFuture<Void> deleteCountry(@PathVariable String id) {
        return commandGateway.sendAndWait(DeleteCountryCommand.from(id));
    }

}
