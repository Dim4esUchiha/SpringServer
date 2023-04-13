package org.dim4es.springapp.services;

import org.dim4es.springapp.models.Tag;
import org.dim4es.springapp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    @Transactional
    public void save(Tag tag){
        tagRepository.save(tag);
    }
}
