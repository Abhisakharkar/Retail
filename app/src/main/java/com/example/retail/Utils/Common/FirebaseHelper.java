package com.example.retail.Utils.Common;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

public class FirebaseHelper {
        private FirebaseAuth firebaseAuth;
        private SharedPreferencesHelper sharedPreferencesHelper;

        @Inject
        public FirebaseHelper(SharedPreferencesHelper sharedPreferencesHelper) {
            firebaseAuth = FirebaseAuth.getInstance();
            this.sharedPreferencesHelper=sharedPreferencesHelper;
        }

        public void signIn(String mail, String pass, SuccessFailureCallback callback) {
            firebaseAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            getToken(callback);
                        } else {
                            task.getException().printStackTrace();
                            callback.onFailure();
                        }
                    });
        }

        public void signUp(String mail, String pass, SuccessFailureCallback callback) {
            firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            getToken(callback);
                        } else {
                            //getException(task);
                            task.getException().printStackTrace();
                            callback.onFailure();
                        }
                    });
        }

        private void getException(Task task){

            try {
                throw task.getException();
            } catch(FirebaseAuthWeakPasswordException e) {
                e.printStackTrace();

            } catch(FirebaseAuthInvalidCredentialsException e) {
                e.printStackTrace();

            } catch(FirebaseAuthUserCollisionException e) {
                e.printStackTrace();

            } catch(Exception e) {
                e.printStackTrace();

            }
        }

        private void getToken(SuccessFailureCallback callback){
                firebaseAuth.getCurrentUser().getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                    @Override
                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                        if (task.isSuccessful()) {
                            String idToken = task.getResult().getToken();
                            sharedPreferencesHelper.setToken(idToken);
                            callback.onSuccess();
                        } else {
                            Log.d(TAG, "onComplete: firebase Get token failed");
                            callback.onFailure();
                        }
                    }
                });
        }


 }


