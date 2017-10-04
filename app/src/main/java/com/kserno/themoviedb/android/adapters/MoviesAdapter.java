package com.kserno.themoviedb.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kserno.themoviedb.R;
import com.kserno.themoviedb.android.listeners.IViewHolderSelectionListener;
import com.kserno.themoviedb.android.viewholders.MovieViewHolder;
import com.kserno.themoviedb.objects.Movie;

import java.util.ArrayList;

/**
 * Created by filipsollar on 4.10.17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<Movie> mMoviesList;
    private LayoutInflater mInflater;
    private IViewHolderSelectionListener<Movie> mListener;

    public MoviesAdapter(Context context, IViewHolderSelectionListener<Movie> listener) {
        mInflater = LayoutInflater.from(context);
        mMoviesList = new ArrayList<>();
        mListener = listener;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.update(mMoviesList, position);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public void addItems(ArrayList<Movie> moviesList) {
        mMoviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mMoviesList.clear();
        notifyDataSetChanged();
    }
}
