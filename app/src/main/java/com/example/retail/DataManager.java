package com.example.retail;

import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Utils.Common.FirebaseHelper;
import com.example.retail.Utils.Common.SharedPreferencesHelper;
import com.example.retail.Utils.Common.VolleyHelper;

import org.json.JSONObject;

import javax.inject.Inject;

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

    public void getMandatoryDataFilled(SuccessFailureCallback callback){
        int mandatoryData=sharedPreferencesHelper.getisMandatoryDataFilled();
       if (mandatoryData==-1){
            volleyHelper.sendRequest("/getProfile", null, null, new JsonDataCallback() {
                @Override
                public void onSuccess(JSONObject responseData) {

                }

                @Override
                public void onFailure(JSONObject errorData) {
                    callback.onFailure();
                }
            });
       }
    }


}
