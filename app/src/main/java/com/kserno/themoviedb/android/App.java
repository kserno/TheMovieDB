package com.kserno.themoviedb.android;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.kserno.themoviedb.R;
import com.kserno.themoviedb.dagger.AppModule;
import com.kserno.themoviedb.dagger.DaggerAppComponent;
import com.kserno.themoviedb.dagger.DaggerInjector;

/**
 * Created by filipsollar on 4.10.17.
 */

public class App extends Application{



    @Override
    public void onCreate() {
        super.onCreate();




        DaggerInjector.set(
                DaggerAppComponent.builder()
                        .appModule(new AppModule(getApplicationContext()))
                        .build()
        );

    }
}


