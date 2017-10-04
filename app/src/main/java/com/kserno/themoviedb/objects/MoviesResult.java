package com.kserno.themoviedb.objects;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by filipsollar on 4.10.17.
 */

public class MoviesResult implements Serializable{

    private ArrayList<Movie> results;

    public MoviesResult() {
    }

    public MoviesResult(ArrayList<Movie> results) {
        this.results = results;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }
}
