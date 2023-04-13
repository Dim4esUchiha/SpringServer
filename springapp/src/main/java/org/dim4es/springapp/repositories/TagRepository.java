package org.dim4es.springapp.repositories;

import org.dim4es.springapp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByTagName(String tagName);
}
