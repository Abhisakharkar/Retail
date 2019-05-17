package com.example.retail.di.Components;


import com.example.retail.SignInActivity;
import com.example.retail.Utils.Common.CredentialValidator;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.SharedPreferencesHelper;
import com.example.retail.Utils.Common.VolleyHelper;
import com.example.retail.di.Modules.AppModules;
import com.example.retail.di.RetailApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModules.class})
public interface AppComponent {
    void inject(RetailApp app);
    void inject(SignInActivity activity);
    SharedPreferencesHelper getSharedPreferencesHelper();
    FirebaseHelper getFirebaseHelper();
    CredentialValidator getCredentialValidator();
    VolleyHelper getVolleyHelper();


    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(RetailApp app);
        AppComponent build();
    }
}
