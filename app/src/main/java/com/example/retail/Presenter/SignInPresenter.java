package com.example.retail.Presenter;

import android.support.design.widget.Snackbar;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Contracts.SignInContract;
import com.example.retail.DataManager;

import javax.inject.Inject;

public class SignInPresenter implements SignInContract.presenter {
    private SignInContract.view signInView;
    private DataManager dataManager;

    public SignInPresenter(SignInContract.view signInView,DataManager dataManager) {
        this.signInView = signInView;
        this.dataManager=dataManager;

    }

    public void signIn(String mail, String password){
        dataManager.signIn(mail, password, new SuccessFailureCallback() {
            @Override
            public void onSuccess() { signInView.gotoHomeActivity(); }
            @Override
            public void onFailure() {
                signInView.showSnackbar("failed", Snackbar.LENGTH_LONG);
            }
        });
    }


}
