package com.pixelignite.repository;

import com.pixelignite.model.Breed;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.Query;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryConfig(cacheName = "BreedCache")
public interface BreedRepository extends IgniteRepository <Breed, Long> {

    List <Breed> getAllBreedsByName(String name);
    @Query("SELECT id FROM Breed WHERE id = ?")
    List < Long > getById(long id, Pageable pageable);

}