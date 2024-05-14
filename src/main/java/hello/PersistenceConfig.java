package hello;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "hello.repository") 
public class PersistenceConfig {
    
}
