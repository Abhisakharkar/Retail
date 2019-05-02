package com.example.retail.Utils.Common;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import javax.inject.Inject;

public class FirebaseHelper {
        private FirebaseAuth firebaseAuth;
        @Inject
        public FirebaseHelper() {
            firebaseAuth = FirebaseAuth.getInstance();
        }

        public void signIn(String mail, String pass, SuccessFailureCallback callback) {
            firebaseAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
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
                            callback.onSuccess();
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
//
//        public void getFirebaseToken(JsonDataCallback callback) {
//            firebaseAuth.addIdTokenListener()
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            String token = task.getResult().getToken();
//                            try {
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("from", "getFirebaseToken");
//                                jsonObject.put("token", token);
//                                callback.onSuccess(jsonObject);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                callback.onFailure(null);
//                            }
//                        } else {
//                            try {
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("from", "getFirebaseToken");
//                                jsonObject.put("error", task.getException());
//
//                                callback.onFailure(jsonObject);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                callback.onFailure(null);
//                            }
//                        }
//                    });
//        }
//
//        public boolean isMailVerified() {
//            return firebaseProvider.getUser().isEmailVerified();
//        }

 }


