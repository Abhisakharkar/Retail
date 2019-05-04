package com.example.retail.Presenter;

import android.support.design.widget.Snackbar;
import android.text.Editable;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Contracts.SignInContract;
import com.example.retail.DataManager;
import com.example.retail.Enum.CredentialEnum;
import com.example.retail.Utils.Common.CredentialValidator;
import com.example.retail.Utils.Common.FirebaseHelper;

import javax.inject.Inject;

public class SignInPresenter implements SignInContract.presenter {
    private SignInContract.view signInView;
    private DataManager dataManager;
    private CredentialValidator credentialValidator;
    private FirebaseHelper firebaseHelper;

    public SignInPresenter(SignInContract.view signInView, DataManager dataManager, CredentialValidator credentialValidator, FirebaseHelper firebaseHelper) {
        this.signInView = signInView;
        this.dataManager=dataManager;
        this.credentialValidator=credentialValidator;
        this.firebaseHelper=firebaseHelper;

    }

    public void signIn(String mail, String password){
        CredentialEnum result=credentialValidator.ValidateForSignIn(mail,password);
        if (result==CredentialEnum.OK){
            firebaseHelper.signIn(mail, password, new SuccessFailureCallback() {
                @Override
                public void onSuccess() {
                    dataManager.getMandatoryDataFilled(new SuccessFailureCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                    signInView.gotoHomeActivity(); }
                @Override
                public void onFailure() {
                    signInView.showSnackbar("failed", Snackbar.LENGTH_LONG);
                }
            });
        }else {
            signInView.showSnackbar(result.toString(),Snackbar.LENGTH_SHORT);
        }




    }


}
