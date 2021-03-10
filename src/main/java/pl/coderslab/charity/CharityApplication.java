package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "java.pl.coderslab.charity.model")
@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

}
