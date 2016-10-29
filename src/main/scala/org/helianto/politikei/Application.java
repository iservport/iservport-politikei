package org.helianto.politikei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"org.helianto.*.service","org.helianto.*.controller"})
@EnableJpaRepositories(basePackages={"org.helianto.*.repository"})
@EntityScan(basePackages={"org.helianto.*.domain"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
