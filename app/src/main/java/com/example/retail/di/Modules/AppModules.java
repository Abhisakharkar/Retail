package com.example.retail.di.Modules;


import android.app.Application;
import android.content.Context;

import com.example.retail.di.RetailApp;
import com.example.retail.di.scope.ForApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModules {
    @Singleton
    @Binds
    abstract Application application(RetailApp app);
}
