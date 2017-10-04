package com.kserno.themoviedb.screen;

import com.kserno.themoviedb.objects.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filipsollar on 4.10.17.
 */

public interface IMainScreen {

    void displayMovies(ArrayList<Movie> moviesList);
    void launchMovieDetailScreen(Movie movie);
    void openSearchDialog();
    void startLoading();
    void noNetwork();
}
