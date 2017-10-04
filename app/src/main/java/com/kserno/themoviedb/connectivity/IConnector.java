package com.kserno.themoviedb.connectivity;

import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.objects.MoviesResult;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by filipsollar on 4.10.17.
 */

public interface IConnector {

    Single<Movie> getMovie(int movieId);
    Single<ArrayList<Movie>> getMovies();
    Single<ArrayList<Movie>> getMovies(String startDate);

    Single<ArrayList<Movie>> getMovies(String startDate, String endDate);
    Single<ArrayList<Movie>> getMovies(String startDate, String endDate, int page);

}
