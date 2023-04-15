package org.dim4es.springapp.controllers;

import org.dim4es.springapp.dto.UserDTO;
import org.dim4es.springapp.models.Person;
import org.dim4es.springapp.models.Tag;
import org.dim4es.springapp.models.User;
import org.dim4es.springapp.services.UserService;
import org.dim4es.springapp.util.UserExceptions.UserErrorResponse;
import org.dim4es.springapp.util.UserExceptions.UserNotCreatedException;
import org.dim4es.springapp.util.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public List<User> getUsers(){
//        // Jackson auto convert Java - objects into JSON
//        return userService.findAll();
//    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUsers(Model model){
        // Jackson auto convert Java - objects into JSON
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getCreationPAge(Model model){
        // Jackson auto convert Java - objects into JSON
        model.addAttribute("user", new User());
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
        System.out.println(user);
        userService.save(user);
        return "redirect:/user";
    }

    ////////////////////
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("user_info", userService.findById(id));
        model.addAttribute("user_id", id);

        Person person = userService.getPersonInfo(id);
        if(person == null)
            person = new Person();
        model.addAttribute("person_info", person);

        return "edit";
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person, @PathVariable("id") int id, Model model){
        User user = userService.findById(id);

        System.out.println(user);
        System.out.println(user.getPerson());
        System.out.println(person);

        user.getPerson().setName(person.getName());
        user.getPerson().setSurName(person.getSurName());
        user.getPerson().setAge(person.getAge());

        System.out.println(user.getPerson());
        userService.save(user);

        return "redirect:/user";
    }

    @RequestMapping(value = "user/{id}/tags", method = RequestMethod.GET)
    public String showTags(@PathVariable("id") int id, Model model){
        model.addAttribute("user_name_tags", userService.findById(id));
        model.addAttribute("person_tags", userService.getPersonInfo(id));
        model.addAttribute("person_tags_info", userService.getPersonInfo(id).getTags());
        model.addAttribute("delete_tag", new Tag());
        return "tags";
    }


    @RequestMapping(value = "user/{id}/tags", method = RequestMethod.POST)
    public String deleteTag(@ModelAttribute Tag tag, @PathVariable("id") int id, Model model){
        System.out.println(tag);
        return "redirect:/user";
    }

//    @GetMapping("/{id}")
//    public User getUser(@PathVariable("id") int id){
//        return userService.findById(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO,
//                                             BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            StringBuilder errorMsg = new StringBuilder();
//            List<FieldError> errors = bindingResult.getFieldErrors();
//
//            for(FieldError error : errors){
//                errorMsg.append(error.getField() + " - ").append(error.getDefaultMessage()).append(";");
//            }
//
//            throw new UserNotCreatedException(errorMsg.toString());
//        }
//
//        userService.save(convertToUser(userDTO));
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    private User convertToUser(UserDTO userDTO){
        User user = new User();

        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
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
