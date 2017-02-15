package de.pschijven.entwicklertag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SwaggerUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerUiApplication.class, args);
	}

	@Bean
	public List<Greeting> greetings() {
		List<Greeting> greetings = new ArrayList<>();
		greetings.add(new Greeting(1L, "Hello World"));
		greetings.add(new Greeting(2L, "Hallo Welt"));
		greetings.add(new Greeting(3L, "Hallo Wereld"));

		return greetings;
	}
}
