package src.rutina.app.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class RutinaAppApplication {
	
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
	return new SecurityConfiguration();
    }

	
	public static void main(String[] args) {
		SpringApplication.run(RutinaAppApplication.class, args);
	}
}
