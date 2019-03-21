package com.pixelignite;


import com.pixelignite.cache.SpringAppConfig;
import com.pixelignite.model.Breed;
import com.pixelignite.model.Dog;
import com.pixelignite.repository.BreedRepository;
import com.pixelignite.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;

public class App {
    private static AnnotationConfigApplicationContext ctx;
    @Autowired
    private static BreedRepository breedRepository;
    @Autowired
    private static DogRepository dogRepository;

    public static void main(String[] args) {
        System.out.println("Spring Data Example!");
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringAppConfig.class);
        ctx.refresh();
        breedRepository = ctx.getBean(BreedRepository.class);
        dogRepository = ctx.getBean(DogRepository.class);

        //fill the repository with data and Save
        Breed collie = new Breed();
        collie.setId(1L);
        collie.setName("collie");

        //save Breed with name collie
        breedRepository.save(1L, collie);
        System.out.println("Add one breed in the repository!");
        // Query the breed
        List< Breed > getAllBreeds = breedRepository.getAllBreedsByName("collie");
        for (Breed breed: getAllBreeds) {
            System.out.println("Breed:" + breed);
        }

        //Add some dogs
        Dog dina = new Dog();
        dina.setName("dina");
        dina.setId(1L);
        dina.setBreedid(1L);

        //Save Dina
        dogRepository.save(2L, dina);
        System.out.println("Dog dina save into the cache!");

        //Query the Dog Dina
        List < Dog > dogs = dogRepository.getDogByName("dina");
        for (Dog dog: dogs) {
            System.out.println("Dog:" + dog);
        }
    }
}
