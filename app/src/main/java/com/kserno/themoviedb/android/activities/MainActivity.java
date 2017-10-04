package com.kserno.themoviedb.android.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kserno.themoviedb.R;
import com.kserno.themoviedb.android.listeners.IViewHolderSelectionListener;
import com.kserno.themoviedb.android.adapters.MoviesAdapter;
import com.kserno.themoviedb.dagger.DaggerInjector;
import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.presenters.MainPresenter;
import com.kserno.themoviedb.screen.IMainScreen;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by filipsollar on 4.10.17.
 */

public class MainActivity extends ABaseActivity implements IMainScreen, DatePickerDialog.OnDateSetListener, IViewHolderSelectionListener<Movie>{


    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fab_search) FloatingActionButton fabSearch;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @BindView(R.id.tv_error) TextView tvError;
    @BindView(R.id.iv_error) ImageView ivError;

    @BindView(R.id.linear_layout_errors) LinearLayout linearLayoutErrors;


    @Inject MainPresenter presenter;

    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mAdapter = new MoviesAdapter(this, this);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, isTablet ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        DaggerInjector.get().inject(this);
        presenter.onCreate(this);
    }

    @OnClick(R.id.fab_search)
    public void searchClicked() {
        presenter.searchClicked(this);
    }

    @OnClick(R.id.bt_error)
    public void errorClicked() {
        presenter.onCreate(this);
    }

    @Override
    public void displayMovies(ArrayList<Movie> moviesList) {
        mAdapter.addItems(moviesList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void launchMovieDetailScreen(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("EXTRA_movie", movie);
        startActivity(intent);
    }

    @Override
    public void openSearchDialog() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override
    public void startLoading() {
        linearLayoutErrors.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mAdapter.clearItems();
    }

    @Override
    public void noNetwork() {
        progressBar.setVisibility(View.GONE);
        linearLayoutErrors.setVisibility(View.VISIBLE);

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        presenter.search(this, calendar);
    }

    @Override
    public void onViewHolderSelected(Movie object) {
        presenter.onViewHolderSelected(this, object);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        int orientationCode =
                isTablet ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientationCode);
    }
}
