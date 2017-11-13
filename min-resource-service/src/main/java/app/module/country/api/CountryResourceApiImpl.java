package app.module.country.api;

import app.error.ResourceEntityNotFoundException;
import app.module.country.entity.CountryEntity;
import app.module.country.repo.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@RestController
public class CountryResourceApiImpl implements CountryResourceApi {
    private final CountryRepository repository;

    public CountryResourceApiImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public CompletableFuture<Page<CountryEntity>> findAll(Pageable pageable) {
        return CompletableFuture.supplyAsync(() -> repository.findAll(pageable));
    }

    @Override
    public CompletableFuture<CountryEntity> findById(@PathVariable String id) {
        return CompletableFuture.supplyAsync(() ->
            Optional.ofNullable(repository.findById(id))
                    .orElseThrow(() -> new ResourceEntityNotFoundException(id))
        );
    }

    @Override
    public CompletableFuture<CountryEntity> findByName(@RequestParam("name") String name) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findByName(name))
                        .orElseThrow(() -> new ResourceEntityNotFoundException("No country with name: " + name))
        );
    }

    private final Page<CountryEntity> EMPTY_PAGE = new PageImpl<>(Collections.emptyList());
    @Override
    public CompletableFuture<Page<CountryEntity>> findByNameLike(@RequestParam("name") String name, Pageable pageable) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findByNameLike(name, pageable))
                        .orElseThrow(() -> new ResourceEntityNotFoundException("No countries with name: " + name))
        );
    }

    @Override
    public CompletableFuture<CountryEntity> findByIso(@RequestParam("iso") String iso) {
        return CompletableFuture.supplyAsync(() ->
                Optional.ofNullable(repository.findByIso(iso))
                        .orElseThrow(() -> new ResourceEntityNotFoundException(iso))
        );
    }
}
