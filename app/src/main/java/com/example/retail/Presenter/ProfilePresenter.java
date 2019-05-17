package com.example.retail.Presenter;

import android.support.design.widget.Snackbar;

import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Contracts.ProfileContract;
import com.example.retail.Enum.ProfileEnum;
import com.example.retail.Utils.Common.SharedPreferencesHelper;
import com.example.retail.Utils.Common.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;


public class ProfilePresenter implements ProfileContract.presenter {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private VolleyHelper volleyHelper;
    private ProfileContract.view profileView;

    public ProfilePresenter(ProfileContract.view profileView,SharedPreferencesHelper sharedPreferencesHelper, VolleyHelper volleyHelper) {
        this.profileView=profileView;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.volleyHelper = volleyHelper;
    }

    public void sendProfile(String enterpriseName,String proprietor,String mobileNo){
        ProfileEnum result=validateProfileData(enterpriseName,proprietor,mobileNo);
        JSONObject body=new JSONObject();
        if (result==ProfileEnum.OK){
            try {

                body.put("enterpriseName",enterpriseName);
                body.put("proprietor",proprietor);
                body.put("mobileNo",mobileNo);
                body.put("latitudeLocation",523526.56);
                body.put("longitudeLocation",264545.26);
            }catch (JSONException e){
                e.printStackTrace();
            }
            volleyHelper.sendRequest("/add_retailer_profile", null, body, new JsonDataCallback() {
                @Override
                public void onSuccess(JSONObject responseData) {
                    profileView.gotoHomeActivity();
                }

                @Override
                public void onFailure(JSONObject errorData) {
                    profileView.showSnackbar("error in send",Snackbar.LENGTH_SHORT);
                }
            });
        }else {
            profileView.showSnackbar(result.toString(), Snackbar.LENGTH_SHORT);
        }


    }

    public ProfileEnum validateProfileData(String enterpriseName, String proprietor, String mobileNo){
        if (enterpriseName.isEmpty()) {
            return ProfileEnum.ENTERPRISE_EMPTY;
        }
        if (proprietor.isEmpty()) {
            return ProfileEnum.PROPIETOR_EMPTY;
        }
        if (mobileNo.isEmpty()) {
            return ProfileEnum.MOBILE_EMPTY;
        }
        return ProfileEnum.OK;

    }
}
