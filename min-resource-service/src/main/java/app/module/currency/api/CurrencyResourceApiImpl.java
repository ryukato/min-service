package app.module.currency.api;

import app.error.ResourceEntityNotFoundException;
import app.module.currency.entity.CurrencyEntity;
import app.module.currency.repo.CurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@RestController
public class CurrencyResourceApiImpl implements CurrencyResourceApi {
    private final CurrencyRepository repository;

    public CurrencyResourceApiImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<Page<CurrencyEntity>> findAll(Pageable pageable) {
        return CompletableFuture.supplyAsync(() -> repository.findAll(pageable));
    }

    @Override
    public CompletableFuture<CurrencyEntity> findById(@PathVariable("id") String id) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findById(id))
                        .orElseThrow(() -> new ResourceEntityNotFoundException("No currency with id: " + id))
        );
    }

    @Override
    public CompletableFuture<CurrencyEntity> findByName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findByName(name))
                        .orElseThrow(() -> new ResourceEntityNotFoundException("No currency with name: " + name))
        );
    }

    @Override
    public CompletableFuture<CurrencyEntity> findByKoreanName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findByKoreanName(name))
                        .orElseThrow(() -> new ResourceEntityNotFoundException("No currency with korean name: " + name))
        );
    }
}
