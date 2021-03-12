package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableJpaRepositories(basePackages = "pl.coderslab.charity.model")
//@ComponentScan(basePackages = "pl.coderslab.charity.repository")
@ComponentScan(basePackages = "pl.coderslab.charity")
@EnableWebMvc
@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

}
