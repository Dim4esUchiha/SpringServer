package org.dim4es.springapp.controllers;

import org.dim4es.springapp.models.Person;
import org.dim4es.springapp.models.User;
import org.dim4es.springapp.services.PersonService;
import org.dim4es.springapp.util.PersonException.PersonErrorResponse;
import org.dim4es.springapp.util.PersonException.PersonNotCreatedException;
import org.dim4es.springapp.util.PersonException.PersonNotFoundException;
import org.dim4es.springapp.util.UserExceptions.UserErrorResponse;
import org.dim4es.springapp.util.UserExceptions.UserNotCreatedException;
import org.dim4es.springapp.util.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPersons(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id){
        return personService.findById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult,
                                             @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors){
                errorMsg.append(error.getField() + " - ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        personService.save(person, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id not found in DB :(",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage() + " :(( ",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
