package com.kserno.themoviedb.objects;

import java.io.Serializable;

/**
 * Created by filipsollar on 4.10.17.
 */

public class Country implements Serializable{
    private String iso_3166_1;
    private String name;

    public Country() {
    }

    public Country(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }
}
