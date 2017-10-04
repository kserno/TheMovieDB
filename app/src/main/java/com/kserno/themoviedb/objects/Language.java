package com.kserno.themoviedb.objects;

import java.io.Serializable;

/**
 * Created by filipsollar on 4.10.17.
 */

public class Language implements Serializable{
    private String iso_639_1;
    private String name;

    public Language() {
    }

    public Language(String iso_639_1, String name) {
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getName() {
        return name;
    }
}
