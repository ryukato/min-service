package app.module.config;

import app.module.country.aggregate.CountryAggregate;
import app.module.currency.aggregate.CurrencyAggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class AggregateRepositoryConfig {
    protected int eventCountForSnapshot =50;

    @Bean
    SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    Snapshotter snapshotter(SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean) throws Exception {
        return springAggregateSnapshotterFactoryBean().getObject();
    }

    @Bean
    Repository<CountryAggregate> countryAggregateRepository(EventStore eventStore, Snapshotter snapshotter) {
        SnapshotTriggerDefinition triggerDefinition = new EventCountSnapshotTriggerDefinition(snapshotter, eventCountForSnapshot);
        return new EventSourcingRepository<>(CountryAggregate.class, eventStore, triggerDefinition);
    }

    @Bean
    Repository<CurrencyAggregate> currencyAggregateRepository(EventStore eventStore, Snapshotter snapshotter) {
        SnapshotTriggerDefinition triggerDefinition = new EventCountSnapshotTriggerDefinition(snapshotter, eventCountForSnapshot);
        return new EventSourcingRepository<>(CurrencyAggregate.class, eventStore, triggerDefinition);
    }
}

