package org.dim4es.springapp.services;

import org.dim4es.springapp.models.Person;
import org.dim4es.springapp.models.User;
import org.dim4es.springapp.repositories.PersonRepository;
import org.dim4es.springapp.repositories.UserRepository;
import org.dim4es.springapp.util.PersonException.PersonNotFoundException;
import org.dim4es.springapp.util.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findById(int id){
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person, int id){
        personRepository.save(person);

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setPerson(person);
        person.setUser(user);
    }
}
