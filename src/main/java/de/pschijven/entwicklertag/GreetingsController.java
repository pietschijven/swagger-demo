package de.pschijven.entwicklertag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Sample CRUD REST controller for Greetings.
 */
@RestController
@RequestMapping("/greeting")
@Api(value = "/greeting", produces = "application/json")
public class GreetingsController {

    private List<Greeting> greetings = new ArrayList<>();

    @Autowired
    public GreetingsController(final List<Greeting> greetings) {
        this.greetings = greetings;
    }

    @GetMapping
    @ApiOperation(value = "Retrieves a list of all available greetings",
            notes = "Very standard REST endpoint",
            response = Greeting.class,
            responseContainer = "List"
    )
    @ApiResponse(code = 200, message = "List of greetings")
    public List<Greeting> showGreetings() {
        return greetings;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieves a specific greeting",
            notes = "The parameter id represents the id of the greeting",
            response = Greeting.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The greeting for the given id", response = Greeting.class),
            @ApiResponse(code = 404, message = "A greeting for the given id does not exist")
    })
    public ResponseEntity<Greeting> showGreeting(@ApiParam("The id of the greeting") @PathVariable("id") final Long id) {
        for (Greeting greeting : greetings) {
            if (greeting.getId().equals(id)) {
                return new ResponseEntity<>(greeting, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ApiOperation(value = "Creates a new greeting",
            notes = "The ID of the new greeting is equal to the current maximum value + 1",
            response = Greeting.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The created greeting", response = Greeting.class)
    })
    public ResponseEntity<Greeting> createGreeting(@ApiParam(value = "The greeting to create (in JSON format)", required = true) @RequestBody final Greeting greeting) {
        addGreeting(greeting);
        return new ResponseEntity<>(greeting, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates a greeting",
            notes = "Only the content of the greeting can be updated",
            response = Greeting.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The updated greeting", response = Greeting.class),
            @ApiResponse(code = 404, message = "A greeting with the given ID does not exist.")
    })
    public ResponseEntity<Greeting> updateGreeting(@ApiParam("The id of the greeting") @PathVariable("id") final Long id,
                                                   @ApiParam(value = "The greeting to update (in JSON format)", required = true) @RequestBody final Greeting greeting) {
        for (Greeting g : greetings) {
            if (g.getId().equals(id)) {
                // Update greeting
                g.setContent(greeting.getContent());
                return new ResponseEntity<>(g, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a greeting",
            notes = "Only removes the greeting if the greeting exists.",
            response = Greeting.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "A greeting with the given ID does not exist.")
    })
    public ResponseEntity<Greeting> deleteGreeting(@ApiParam("The id of the greeting") @PathVariable("id") final Long id) {
        for (Greeting greeting : greetings) {
            if (greeting.getId().equals(id)) {
                // Remove greeting
                greetings.remove(greeting);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        // Greeting does not exist
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void addGreeting(final Greeting greeting) {
        Optional<Long> maximumId = greetings.stream().map(Greeting::getId).max(Long::compare);

        if (maximumId.isPresent()) {
            greeting.setId(maximumId.get() + 1L);
            greetings.add(greeting);
        } else {
            greeting.setId(1L);
            greetings.add(greeting);
        }
    }
}
