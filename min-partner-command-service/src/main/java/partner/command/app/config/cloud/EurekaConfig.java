package partner.command.app.config.cloud;


import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableEurekaClient
@Profile({"cloud"})
public class EurekaConfig {
}