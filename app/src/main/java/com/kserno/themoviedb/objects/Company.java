package com.kserno.themoviedb.objects;


import java.io.Serializable;

/**
 * Created by filipsollar on 4.10.17.
 */

public class Company implements Serializable{
    private String name;
    private int id;


    public Company() {
    }

    public Company(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
