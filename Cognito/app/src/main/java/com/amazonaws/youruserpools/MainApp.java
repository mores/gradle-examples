package com.amazonaws.youruserpools;

import android.app.Application;

public class MainApp extends Application {

    private static MainApp instance;

    public static MainApp getInstance()
    {
        return instance;
    }

    public static android.content.Context getContext()
    {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
