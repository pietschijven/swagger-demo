package de.pschijven.entwicklertag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Sample REST controller.
 */
@RestController
@RequestMapping("/greeting")
public class GreetingsController {

    private final List<Greeting> greetings;

    public GreetingsController(final List<Greeting> greetings) {
        this.greetings = greetings;
    }

    @GetMapping
    public List<Greeting> showGreetings() {
        return greetings;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> showGreeting(@PathVariable("id") final Long id) {
        for (Greeting greeting : greetings) {
            if (greeting.getId().equals(id)) {
                return new ResponseEntity<>(greeting, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
