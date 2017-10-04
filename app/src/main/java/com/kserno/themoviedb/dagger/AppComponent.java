package com.kserno.themoviedb.dagger;

import com.kserno.themoviedb.android.viewholders.MovieViewHolder;
import com.kserno.themoviedb.android.activities.MainActivity;
import com.kserno.themoviedb.android.activities.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by filipsollar on 4.10.17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(MovieViewHolder holder);

    void inject(MovieDetailActivity activity);
}
