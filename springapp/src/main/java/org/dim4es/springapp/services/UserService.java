package org.dim4es.springapp.services;


import org.dim4es.springapp.models.Person;
import org.dim4es.springapp.models.User;
import org.dim4es.springapp.repositories.UserRepository;
import org.dim4es.springapp.util.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void save(User user){
        enrichUser(user);
        userRepository.save(user);
    }

    public Person getPersonInfo(int id){
        Person person = findById(id).getPerson();
        return person;
    }


    private void enrichUser(User user){
        user.setLastLocation("MINSK");
        user.setPerson(null);
    }

}
