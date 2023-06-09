package org.dim4es.springapp.repositories;

import org.dim4es.springapp.models.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
}
