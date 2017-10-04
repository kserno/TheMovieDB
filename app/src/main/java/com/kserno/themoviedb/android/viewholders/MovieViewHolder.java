package com.kserno.themoviedb.android.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kserno.themoviedb.R;
import com.kserno.themoviedb.android.listeners.IViewHolderSelectionListener;
import com.kserno.themoviedb.dagger.DaggerInjector;
import com.kserno.themoviedb.objects.Movie;
import com.kserno.themoviedb.presenters.MovieHolderPresenter;
import com.kserno.themoviedb.screen.IMovieHolderScreen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by filipsollar on 4.10.17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements IMovieHolderScreen{

    @BindView(R.id.iv_header) ImageView ivHeader;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_tagline) TextView tvTagline;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Inject MovieHolderPresenter presenter;

    private IViewHolderSelectionListener<Movie> mListener;

    private Movie mMovie;


    public MovieViewHolder(View itemView, IViewHolderSelectionListener<Movie> listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        DaggerInjector.get().inject(this);
        mListener = listener;
        itemView.setOnClickListener(v -> {
                if (mMovie != null && mMovie.getTitle() != null) {
                    mListener.onViewHolderSelected(mMovie);
                }
            }
        );
    }

    public void update(ArrayList<Movie> moviesList, int position) {
        presenter.update(this, position, moviesList);
    }

    @Override
    public void startLoading() {
        tvTagline.setVisibility(View.INVISIBLE);
        tvTitle.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        ivHeader.setImageResource(R.mipmap.placeholder);
    }

    @Override
    public void displayMovie(Movie movie) {
        mMovie = movie;


        Picasso.with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/original" + movie.getPosterPath())
                .placeholder(R.mipmap.placeholder)
                .into(ivHeader);

        tvTitle.setText(movie.getTitle());
        tvTagline.setText(movie.getTagline());

        tvTagline.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
