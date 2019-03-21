package com.pixelignite.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.Date;


public class Dog implements Serializable {


    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField(index = true)
    private String name;
    @QuerySqlField(index = true)
    private Long breedid;


    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breedid=" + breedid +

                '}';
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getBreedid() {
        return breedid;
    }
    public void setBreedid(Long breedid) {
        this.breedid = breedid;
    }

}
