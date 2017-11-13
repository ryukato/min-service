package app.module.currency.api;

import app.module.currency.entity.CurrencyEntity;
import app.currency.model.Currency;
import app.module.currency.repo.CurrencyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
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
        return CompletableFuture.supplyAsync(() -> repository.findById(id));
    }

    @Override
    public CompletableFuture<CurrencyEntity> findByName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() -> repository.findByName(name));
    }

    @Override
    public CompletableFuture<CurrencyEntity> findByKoreanName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() -> repository.findByKoreanName(name));
    }
}
