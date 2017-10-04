package com.kserno.themoviedb.dagger;

import android.content.Context;

import com.kserno.themoviedb.connectivity.Connector;
import com.kserno.themoviedb.connectivity.IConnector;

import dagger.Module;
import dagger.Provides;

/**
 * Created by filipsollar on 4.10.17.
 */
@Module
public class AppModule {

    private Context mAppContext;

    public AppModule(Context mAppContext) {
        this.mAppContext = mAppContext;
    }

    @Provides
    public Context getAppContext() {
        return mAppContext;
    }

    @Provides
    public IConnector getConnector(Connector connector) {
        return connector;
    }
}
