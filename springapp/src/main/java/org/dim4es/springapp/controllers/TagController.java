package org.dim4es.springapp.controllers;

import org.dim4es.springapp.models.Tag;
import org.dim4es.springapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getTags(){
        return tagService.findAll();
    }

    @PostMapping("/{id}_{tag_name}")
    public void addTagToPerson(@PathVariable("id") int id,
                               @PathVariable("tag_name") String tag){
        tagService.addTagToPerson(tag, id);
    }

    @PostMapping
    public void create(@RequestBody Tag tag){
        tagService.save(tag);
    }
}
