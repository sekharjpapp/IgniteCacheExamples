package com.pixelignite.repository;

import com.pixelignite.model.Dog;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryConfig(cacheName = "DogCache")
public interface DogRepository extends IgniteRepository < Dog, Long > {

    List <Dog> getDogByName(String name);
    Dog getDogById(Long id);

}