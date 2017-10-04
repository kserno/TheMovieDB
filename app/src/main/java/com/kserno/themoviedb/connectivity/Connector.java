package com.kserno.themoviedb.connectivity;

import android.content.Context;

import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.objects.MoviesResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by filipsollar on 4.10.17.
 */
@Singleton
public class Connector implements IConnector {

    private static final String TAG = Connector.class.getSimpleName();
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private IRetrofitConnector mRetrofitConnector;

    private static final String API_KEY = "89f4e1e65fd2f2624b2e7eaaff490030";

    @Inject
    public Connector(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        mRetrofitConnector = retrofit.create(IRetrofitConnector.class);

    }

    @Override
    public Single<Movie> getMovie(int movieId) {
        return mRetrofitConnector.getMovieDetails(movieId, API_KEY);
    }

    @Override
    public Single<ArrayList<Movie>> getMovies() {
        return mRetrofitConnector.getMovies(API_KEY)
                .map(MoviesResult::getResults);
    }

    @Override
    public Single<ArrayList<Movie>> getMovies(String startDate) {
        return mRetrofitConnector.getMovies(API_KEY, startDate)
                .map(MoviesResult::getResults);
    }

    @Override
    public Single<ArrayList<Movie>> getMovies(String startDate, String endDate) {
        return mRetrofitConnector.getMovies(API_KEY, startDate, endDate)
                .map(MoviesResult::getResults);
    }

    @Override
    public Single<ArrayList<Movie>> getMovies(String startDate, String endDate, int page) {
        return mRetrofitConnector.getMovies(API_KEY, startDate, endDate, page)
                .map(MoviesResult::getResults);
    }
}
