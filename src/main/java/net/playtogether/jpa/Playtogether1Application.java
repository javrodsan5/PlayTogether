package net.playtogether.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@SpringBootApplication
public class Playtogether1Application {

	public static void main(String[] args) {
		SpringApplication.run(Playtogether1Application.class, args);
	}

}
