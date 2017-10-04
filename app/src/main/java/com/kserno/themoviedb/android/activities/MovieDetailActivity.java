package com.kserno.themoviedb.android.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kserno.themoviedb.R;
import com.kserno.themoviedb.dagger.DaggerInjector;
import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.presenters.MovieDetailPresenter;
import com.kserno.themoviedb.screen.IMovieDetailScreen;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import javax.inject.Inject;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Filip Sollar
 * Created by filipsollar on 4.10.17.
 */

public class MovieDetailActivity extends ABaseActivity implements IMovieDetailScreen {

    @Inject MovieDetailPresenter presenter;

    @BindView(R.id.iv_header) ImageView ivHeader;
    @BindView(R.id.tv_overview) TextView tvOverview;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_countries) TextView tvCountries;
    @BindView(R.id.tv_genres) TextView tvGenres;
    @BindView(R.id.tv_rating) TextView tvRating;
    @BindView(R.id.tv_title_original) TextView tvTitleOriginal;
    @BindView(R.id.tv_production_companies) TextView tvProductionCompanies;
    @BindView(R.id.tv_languages) TextView tvLanguages;
    @BindView(R.id.tv_revenue) TextView tvRevenue;
    @BindView(R.id.tv_budget) TextView tvBudget;
    @BindView(R.id.tv_release_year) TextView tvReleaseYear;

    @BindView(R.id.bt_imdb) Button btImdb;
    @BindView(R.id.bt_website) Button btWebsite;


    private Movie mMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        setContentView(R.layout.activity_movie_detail);
        super.onCreate(savedInstanceState);
        DaggerInjector.get().inject(this);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        mMovie = (Movie) intent.getSerializableExtra("EXTRA_movie");

        assert mMovie != null;

        presenter.onCreate(this, mMovie);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayMovie(Movie movie) {

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                .placeholder(R.mipmap.placeholder)
                .into(ivHeader);

        tvOverview.setText(movie.getOverview());
        tvTitle.setText(movie.getTitle());
        tvReleaseYear.setText(movie.getReleaseDate());
        tvBudget.setText(String.format(Locale.US, "%d $", movie.getBudget()));
        tvRevenue.setText(String.format(Locale.US, "%d $", movie.getRevenue()));

        displayProductionCountries(movie);
        displayProductionCompanies(movie);
        displayLanguages(movie);

        tvRating.setText(String.valueOf(movie.getVoteAverage()));
        tvTitleOriginal.setText(movie.getOfficialTitle());

        displayGenres(movie);
        displayButtons(movie);

    }

    private void displayProductionCompanies(Movie movie) {
        if (movie.getProductionCompanies() != null && movie.getProductionCompanies().length > 0) {
            StringBuilder sb = new StringBuilder(movie.getProductionCompanies()[0].getName());
            for (int i = 1; i < movie.getProductionCountries().length; i++) {
                sb.append("/ ");
                sb.append(movie.getProductionCompanies()[i].getName());
            }
            tvProductionCompanies.setText(sb.toString());
        }

    }

    private void displayLanguages(Movie movie) {
        if (movie.getSpokenLanguages() != null && movie.getSpokenLanguages().length > 0) {
            StringBuilder sb = new StringBuilder(movie.getSpokenLanguages()[0].getName());

            for (int i = 1; i < movie.getSpokenLanguages().length; i++) {
                sb.append("/ ");
                sb.append(movie.getSpokenLanguages()[i].getName());
            }
            tvLanguages.setText(sb.toString());
        }
    }

    private void displayProductionCountries(Movie movie) {
        if (movie.getProductionCountries() != null && movie.getProductionCountries().length > 0) {
            StringBuilder sb = new StringBuilder(movie.getProductionCountries()[0].getName());
            for (int i = 1; i < movie.getProductionCountries().length; i++) {
                sb.append("/ ");
                sb.append(movie.getProductionCountries()[i].getName());

            }
            tvCountries.setText(sb.toString());
        }
    }

    private void displayGenres(Movie movie) {
        // appends genres together
        if (movie.getGenres() != null && movie.getGenres().length > 0) {
            StringBuilder sb = new StringBuilder(movie.getGenres()[0].getName());
            for (int i = 1; i < movie.getGenres().length; i++) {
                sb.append("/ ");
                sb.append(movie.getGenres()[i].getName());
            }
            tvGenres.setText(sb.toString());
        }
    }

    private void displayButtons(Movie movie) {
        int visibilityCode = movie.getHomepage() != null && !movie.getHomepage().isEmpty() ? View.VISIBLE : View.GONE;
        btWebsite.setVisibility(visibilityCode);

        visibilityCode = movie.getImdbId()!= null && !movie.getImdbId().isEmpty() ? View.VISIBLE : View.GONE;
        btImdb.setVisibility(visibilityCode);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        int orientationCode =
                isTablet ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientationCode);
    }

    @OnClick(R.id.bt_website)
    public void onWebsiteClick() {
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMovie.getHomepage()));
        startActivity(websiteIntent);
    }


    @OnClick(R.id.bt_imdb)
    public void onImdbClick() {
        Intent imdbIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://imdb.com/title/tt0137523/" + mMovie.getImdbId()));
        startActivity(imdbIntent);
    }
}
