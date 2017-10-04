package com.kserno.themoviedb.presenters;

import com.kserno.themoviedb.connectivity.IConnector;
import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.screen.IMovieHolderScreen;


import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by filipsollar on 4.10.17.
 */

public class MovieHolderPresenter {

    private Disposable mDisposable;

    private IConnector mConnector;


    @Inject
    public MovieHolderPresenter(IConnector connector) {
        mConnector = connector;
    }

    public void update(IMovieHolderScreen screen, int position, ArrayList<Movie> moviesList){

        screen.startLoading();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }

        Movie movie = moviesList.get(position);

        if (movie.getTitle() == null) {
            mDisposable =
                mConnector.getMovie(movie.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            detailedMovie -> {
                                moviesList.set(position, detailedMovie);
                                screen.displayMovie(detailedMovie);
                            },
                            Throwable::printStackTrace
                    );

        } else {
            screen.displayMovie(movie);
        }






    }
}
