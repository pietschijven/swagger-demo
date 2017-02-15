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
		greetings.add(greeting(1L, "Hello World"));
		greetings.add(greeting(2L, "Hallo Welt"));
		greetings.add(greeting(3L, "Hallo Wereld"));

		return greetings;
	}

    private Greeting greeting(final Long id, final String content) {
	    Greeting greeting = new Greeting();
	    greeting.setId(id);
	    greeting.setContent(content);
        return greeting;
    }
}
