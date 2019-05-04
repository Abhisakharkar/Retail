package com.example.retail;

import android.app.Application;
import android.content.Context;

import com.example.retail.Components.AppComponent;
//import com.example.retail.Components.DaggerAppComponent;

public class RetailApp extends Application {
    private AppComponent appComponent;
    private Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=getApplicationContext();
//        appComponent= DaggerAppComponent.builder()
//                .appContext(applicationContext)
//                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
