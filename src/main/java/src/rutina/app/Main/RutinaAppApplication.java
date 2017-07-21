package src.rutina.app.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



/*
 * Clase principal del servicio web, configurada para la inicialización
 * con Spring Boot y despliegue en tomcat 
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

/*@SpringBootApplication
public class RutinaAppApplication {
	
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
	return new SecurityConfiguration();
    }

	
	public static void main(String[] args) {
		SpringApplication.run(RutinaAppApplication.class, args);
	}
}*/

@SpringBootApplication
public class RutinaAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RutinaAppApplication.class);
    }
    
    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
	return new SecurityConfiguration();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RutinaAppApplication.class, args);
    }

}