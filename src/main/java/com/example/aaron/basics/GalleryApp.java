package com.example.aaron.basics;

import android.app.Application;
import android.content.res.Configuration;

public class GalleryApp extends Application {
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
