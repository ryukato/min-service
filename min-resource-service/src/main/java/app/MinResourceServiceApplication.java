package app;

import app.repo.converter.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication
public class MinResourceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinResourceServiceApplication.class, args);
    }

    @SuppressWarnings("unused")
    @Configuration
    static class InitCommandJob implements CommandLineRunner {
        private final MongoTemplate mongoTemplate;

        @Autowired
        public InitCommandJob(MongoTemplate mongoTemplate) {
            this.mongoTemplate = mongoTemplate;
        }

        @Override
        public void run(String... strings) throws Exception {
            // do nothing
        }
    }

    @SuppressWarnings("unused")
    @Bean
    public MongoDbFactory mongoDbFactory(MongoClientURI mongoClientURI) throws UnknownHostException {
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @SuppressWarnings("unused")
    @Bean
    public MongoClientURI mongoClientURI(@Value("${spring.data.mongodb.uri}") String uri) {
        return new MongoClientURI(uri);
    }

    @SuppressWarnings("unused")
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient();
    }

    @SuppressWarnings("unused")
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory),  new MongoMappingContext());
        converter.setCustomConversions(new CustomConversions(Arrays.asList(
                new DbObject2CountryConverter(),
                new Country2DbObjectConverter(),
                new DbObject2CurrencyConverter(),
                new Currency2DbObjectConverter(),
                new LocalDateTimeToStringConverter(),
                new StringToLocalDateTimeConverter()
        )));
        converter.afterPropertiesSet();
        return converter;
    }

    @SuppressWarnings("unused")
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter mappingMongoConverter) {
        return new MongoTemplate(mongoDbFactory, mappingMongoConverter);
    }
}

@RefreshScope
@RestController
class ConfigTestController {

    @RequestMapping(value = "/test/message", method = RequestMethod.GET)
    public String testMessage(
            @Value("${spring.application.name}") String appName
    ) {
        return appName;
    }

}
