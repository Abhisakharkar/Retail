package com.example.retail.Presenter;

import android.support.design.widget.Snackbar;
import android.util.Log;


import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Contracts.SignInContract;
import com.example.retail.DataManager;
import com.example.retail.Enum.CredentialEnum;
import com.example.retail.Utils.Common.CredentialValidator;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

public class SignInPresenter implements SignInContract.presenter {
    private SignInContract.view signInView;
    private DataManager dataManager;
    private CredentialValidator credentialValidator;
    private FirebaseHelper firebaseHelper;
    private VolleyHelper volleyHelper;

    public SignInPresenter(SignInContract.view signInView, CredentialValidator credentialValidator, FirebaseHelper firebaseHelper, VolleyHelper volleyHelper) {
        this.signInView = signInView;
        this.dataManager=dataManager;
        this.credentialValidator=credentialValidator;
        this.firebaseHelper=firebaseHelper;
        this.volleyHelper=volleyHelper;

    }

    public void signIn(String mail, String password){
        CredentialEnum result=credentialValidator.ValidateForSignIn(mail,password);
        if (result==CredentialEnum.OK){
            firebaseHelper.signIn(mail, password, new SuccessFailureCallback() {
                @Override
                public void onSuccess() {
                    volleyHelper.sendRequest("/getProfile", null, null, new JsonDataCallback() {
                       @Override
                       public void onSuccess(JSONObject responseData) {
                          try {
                              boolean manData=responseData.getBoolean("mandatoryData");
                              if (manData){
                                  boolean verificationStatus=responseData.getBoolean("verificationStatus");
                                  if (verificationStatus){
                                      signInView.gotoHomeActivity();
                                  }else
                                      signInView.showSnackbar("your profile has not been verified yet. you will be allowed to login after verification",Snackbar.LENGTH_SHORT);


                              }else
                                  signInView.gotoProfileActivity();
                          }catch (JSONException e){
                              signInView.gotoProfileActivity();
                          }
                       }

                       @Override
                       public void onFailure(JSONObject errorData) {
                           Log.d(TAG, "onFailure: error getting profile");
                           signInView.showSnackbar("error getting profile", Snackbar.LENGTH_LONG);
                       }
                   });
                }
                @Override
                public void onFailure() {
                    signInView.showSnackbar("sign In failed", Snackbar.LENGTH_LONG);
                }
            });
        }else {
            signInView.showSnackbar(result.toString(),Snackbar.LENGTH_SHORT);
        }




    }


}
