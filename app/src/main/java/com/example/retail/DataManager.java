package com.example.retail;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Utils.Common.FirebaseHelper;

import javax.inject.Inject;

public class DataManager {
    private FirebaseHelper firebaseHelper;
    @Inject
    public DataManager(FirebaseHelper firebaseHelper) {
        this.firebaseHelper = firebaseHelper;
    }

    public void signIn(String mail, String password, SuccessFailureCallback callback){
        firebaseHelper.signIn(mail, password, new SuccessFailureCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
            @Override
            public void onFailure() {
                callback.onFailure();

            }
        });

    }

    public void signUp(String mail,String password,SuccessFailureCallback callback){
        firebaseHelper.signUp(mail, password, new SuccessFailureCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });

    }


}
