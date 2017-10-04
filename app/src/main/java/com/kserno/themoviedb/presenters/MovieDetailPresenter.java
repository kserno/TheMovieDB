package com.kserno.themoviedb.presenters;

import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.screen.IMovieDetailScreen;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by filipsollar on 4.10.17.
 */
@Singleton
public class MovieDetailPresenter  {

    @Inject
    public MovieDetailPresenter() {
        // no need for constructor
    }



    public void onCreate(IMovieDetailScreen screen, Movie movie) {
        screen.displayMovie(movie);

    }
}
