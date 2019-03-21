package com.pixelignite;

import com.pixelignite.cache.SpringAppConfig;
import com.pixelignite.model.Breed;
import com.pixelignite.model.Dog;
import com.pixelignite.repository.BreedRepository;
import com.pixelignite.repository.DogRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.pixelignite.repository")
public class DogBreedApp {

    @Autowired
    private static BreedRepository breedRepository;
    @Autowired
    private static DogRepository dogRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx= SpringApplication.run(DogBreedApp.class,args);
        try (Ignite ignite = Ignition.start("F:\\apache-ignite\\apache-ignite-2.7.0-bin\\examples\\config\\example-ignite.xml")) {

            IgniteCompute compute = ignite.compute();
            compute.broadcast(()-> System.out.println("Hello Node"));

            System.out.println("Spring Data Example!");
            ctx = new AnnotationConfigApplicationContext();
            ((AnnotationConfigApplicationContext) ctx).register(SpringAppConfig.class);
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

            List<Breed> getAllBreeds = breedRepository.getAllBreedsByName("collie");
            for (Breed breed : getAllBreeds) {
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
            List<Dog> dogs = dogRepository.getDogByName("dina");
            for (Dog dog : dogs) {
                System.out.println("Dog:" + dog);
            }
        }
    }
}
