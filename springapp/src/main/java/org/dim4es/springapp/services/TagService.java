package org.dim4es.springapp.services;

import org.dim4es.springapp.models.Person;
import org.dim4es.springapp.models.Tag;
import org.dim4es.springapp.repositories.PersonRepository;
import org.dim4es.springapp.repositories.TagRepository;
import org.dim4es.springapp.util.PersonException.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;
    private final PersonRepository personRepository;

    @Autowired
    public TagService(TagRepository tagRepository, PersonRepository personRepository) {
        this.tagRepository = tagRepository;
        this.personRepository = personRepository;
    }

    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    @Transactional
    public void save(Tag tag){
        tagRepository.save(tag);
    }

    @Transactional
    public void addTagToPerson(String tag, int id){
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        Tag foundTag = tagRepository.findByTagName(tag);

        person.addTag(foundTag);
        foundTag.addPerson(person);
    }

}
