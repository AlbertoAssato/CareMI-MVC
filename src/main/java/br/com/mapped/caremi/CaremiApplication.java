package br.com.mapped.caremi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CaremiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaremiApplication.class, args);
	}

}
