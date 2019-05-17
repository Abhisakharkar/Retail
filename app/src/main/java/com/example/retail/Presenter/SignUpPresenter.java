package com.example.retail.Presenter;

import android.support.design.widget.Snackbar;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Contracts.SignUpContract;
import com.example.retail.Enum.CredentialEnum;
import com.example.retail.Utils.Common.CredentialValidator;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.VolleyHelper;

public class SignUpPresenter implements SignUpContract.presenter {
    private FirebaseHelper firebaseHelper;
    private CredentialValidator credentialValidator;
    private SignUpContract.view signUpView;

    public SignUpPresenter(SignUpContract.view signUpView,FirebaseHelper firebaseHelper,CredentialValidator credentialValidator) {
        this.firebaseHelper = firebaseHelper;
        this.credentialValidator=credentialValidator;
        this.signUpView=signUpView;
    }


    public void signUp(String mail,String password,String confirmPass){
        CredentialEnum result=credentialValidator.ValidateForSignUp(mail,password,confirmPass);
        if (result==CredentialEnum.OK){
                firebaseHelper.signUp(mail, password, new SuccessFailureCallback() {
                    @Override
                    public void onSuccess() {
                        signUpView.gotoProfileActivity();
                    }
                    @Override
                    public void onFailure() {
                        signUpView.showSnackbar("signUp failed",Snackbar.LENGTH_SHORT);
                    }
                });
        }else {
            signUpView.showSnackbar(result.toString(), Snackbar.LENGTH_SHORT);
        }
    }

}
