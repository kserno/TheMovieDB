package com.kserno.themoviedb.objects;

import java.io.Serializable;

/**
 * Created by filipsollar on 4.10.17.
 */

public class Genre implements Serializable{
    private int id;
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
