package com.example.retail;

import android.util.Log;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.SharedPreferencesHelper;
import com.example.retail.Utils.Common.VolleyHelper;

import org.json.JSONObject;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

public class DataManager {
    private FirebaseHelper firebaseHelper;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private VolleyHelper volleyHelper;
    @Inject
    public DataManager(FirebaseHelper firebaseHelper, SharedPreferencesHelper sharedPreferencesHelper, VolleyHelper volleyHelper) {
        this.firebaseHelper = firebaseHelper;
        this.sharedPreferencesHelper=sharedPreferencesHelper;
        this.volleyHelper=volleyHelper;
    }

    public void getProfile(JsonDataCallback callback){
            volleyHelper.sendRequest("/getProfile", null, null, new JsonDataCallback() {
                @Override
                public void onSuccess(JSONObject responseData) {
                    callback.onSuccess(responseData);
                }

                @Override
                public void onFailure(JSONObject errorData) {
                    Log.d(TAG, "onFailure: " +errorData);
                    callback.onFailure(errorData);
                }
            });
    }


}
