package app.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.cdi.MongoRepositoryBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;
import org.springframework.data.repository.query.QueryLookupStrategy;

@SuppressWarnings("unused")
@Configuration
@EnableMongoRepositories(
        basePackages = "app.module",
        includeFilters = {
                @Filter(type = FilterType.REGEX, pattern = ".*Repository")
        },
        excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = ".*Test.*Repository")
        },
        queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND
)
public class MongoRepositoryConfig {

    @Bean
    MongoRepositoryFactory mongoRepositoryFactory(MongoTemplate mongoTemplate) {
        return new MongoRepositoryFactory(mongoTemplate);
    }
}
