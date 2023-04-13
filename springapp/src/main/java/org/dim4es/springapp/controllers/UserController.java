package org.dim4es.springapp.controllers;

import org.dim4es.springapp.models.User;
import org.dim4es.springapp.services.UserService;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        // Jackson auto convert Java - objects into JSON
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors){
                errorMsg.append(error.getField() + " - ").append(error.getDefaultMessage()).append(";");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }

        userService.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
        UserErrorResponse response = new UserErrorResponse(
            "User with this id not found in DB :(",
                    System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage() + " :( ",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
