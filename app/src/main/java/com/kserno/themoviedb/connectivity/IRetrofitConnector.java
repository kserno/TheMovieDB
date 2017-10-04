package com.kserno.themoviedb.connectivity;

import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.objects.MoviesResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by filipsollar on 4.10.17.
 */

public interface IRetrofitConnector {
    @GET("movie/changes")
    Single<MoviesResult> getMovies(@Query("api_key") String apiKey);

    @GET("movie/changes")
    Single<MoviesResult> getMovies(
            @Query("api_key") String apiKey,
            @Query("start_date") String startDate,
            @Query("end_date") String endDate
    );

    @GET("movie/changes")
    Single<MoviesResult> getMovies(
            @Query("api_key") String apiKey,
            @Query("start_date") String startDate
    );

    @GET("movie/changes")
    Single<MoviesResult> getMovies(
            @Query("api_key") String apiKey,
            @Query("start_date") String startDate,
            @Query("end_date") String endDate,
            @Query("page") int page
    );

    @GET("movie/{movie_id}")
    Single<Movie> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );
}
