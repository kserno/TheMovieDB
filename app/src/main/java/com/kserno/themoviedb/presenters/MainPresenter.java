package com.kserno.themoviedb.presenters;

import android.util.Log;

import com.kserno.themoviedb.connectivity.IConnector;
import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.screen.IMainScreen;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by filipsollar on 4.10.17.
 */
@Singleton
public class MainPresenter  {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private IConnector mConnector;

    @Inject
    public MainPresenter(IConnector connector) {
        mConnector = connector;
    }

    public void onCreate(IMainScreen screen) {
        screen.startLoading();

        mConnector.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        screen::displayMovies,
                        e -> {
                            Log.e(TAG, "error", e);
                            screen.noNetwork();
                        }
                );


    }




    public void searchClicked(IMainScreen screen) {
        screen.openSearchDialog();
    }

    public void search(IMainScreen screen, Calendar calendar) {
        String formattedDate =
                String.format(
                        Locale.US,
                        "%d-%d-%d",
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR)
                );

        screen.startLoading();
        mConnector.getMovies(formattedDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        screen::displayMovies,
                        e -> {
                            Log.e(TAG, "error", e);
                            screen.noNetwork();
                        }
                );

    }

    public void onViewHolderSelected(IMainScreen screen, Movie movie) {
        screen.launchMovieDetailScreen(movie);
    }
}
