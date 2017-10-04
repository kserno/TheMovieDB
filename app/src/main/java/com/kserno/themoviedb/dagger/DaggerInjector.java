package com.kserno.themoviedb.dagger;

/**
 * Created by filipsollar on 4.10.17.
 */

public class DaggerInjector {
    private static AppComponent sAppComponent;

    public static void set(AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    public static AppComponent get() {
        return sAppComponent;
    }
}
