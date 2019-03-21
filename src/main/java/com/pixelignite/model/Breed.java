package com.pixelignite.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Breed implements Serializable {


    @QuerySqlField(index = true)

    private Long id;
    @QuerySqlField(index = true)
    private String name;

    public Breed() {
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
    @Override
    public String toString() {
        return "Breed{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
