//package com.example.retail.di.Components;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Build;
//
//import com.example.retail.Contracts.SignInContract;
//import com.example.retail.Presenter.SignInPresenter;
//
//import javax.inject.Named;
//
//import dagger.BindsInstance;
//import dagger.Component;
//
//@PerActivity
//@Component(dependencies = AppComponent.class)
//public interface ActivityComponent {
//
//    SignInPresenter getSignInPresenter();
//
//    @Component.Builder
//    interface Builder {
//        //@BindsInstance
//        Builder Activity(Activity activity);
//        Builder ActivityContext(@Named("activityContext") Context activityContext);
//        Builder SignInView(SignInContract.view signInView);
//        ActivityComponent Build();
//    }
//}
