package com.example.retail.di;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.retail.R;
import com.example.retail.di.Components.AppComponent;
//import com.example.retail.di.Components.DaggerAppComponent;
import com.example.retail.di.Components.DaggerAppComponent;
import com.example.retail.di.Modules.AppModules;
//import com.example.retail.di.Components.DaggerAppComponent;

import java.io.InputStream;

import static android.content.ContentValues.TAG;
//import com.example.retail.di.Components.DaggerAppComponent;

public class RetailApp extends Application {
    private AppComponent appComponent;
    private Context applicationContext;
    private RetailApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        applicationContext=this.getApplicationContext();
        if (applicationContext==null){
            Log.d(TAG, "onCreate: "+ "context is null ");
        }else {
            appComponent = DaggerAppComponent.builder()
                    .application(this)
                    .build();
            appComponent.inject(this);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
