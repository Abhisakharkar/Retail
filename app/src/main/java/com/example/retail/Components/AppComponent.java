package com.example.retail.Components;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.retail.DataManager;
import com.example.retail.Presenter.SignInPresenter;
import com.example.retail.Utils.Common.CredentialValidator;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.SharedPreferencesHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component
public interface AppComponent {
    FirebaseHelper getFirebaseHelper();
    DataManager getDataManager();
    SharedPreferencesHelper getSharedPreferencesHelper();
    CredentialValidator getCredentialValidator();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder appContext(@Named ("applicationContext") Context applicationContext);
        AppComponent build();
    }
}
