package com.example.retail.Utils.Common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.retail.di.RetailApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private RetailApp mapplicationContext;

    @Inject
    public SharedPreferencesHelper(RetailApp applicationContext) {
        mapplicationContext=applicationContext;
        this.sharedPreferences=mapplicationContext.getApplicationContext().getSharedPreferences("myPref",0);
        editor=this.sharedPreferences.edit();
    }

    public JSONObject getProfile(){
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("userId",sharedPreferences.getString("userId",null));
            jsonObject.put("mandatoryData",sharedPreferences.getBoolean("mandatoryData",false));
            jsonObject.put("enterpriseName",sharedPreferences.getString("enterpriseName",null));
            jsonObject.put("proprietor",sharedPreferences.getString("proprietor",null));
            jsonObject.put("mobileNo",sharedPreferences.getLong("mobileNo",0));
            jsonObject.put("latitudeLocation",sharedPreferences.getFloat("latitudeLocation",0));
            jsonObject.put("longitudeLocation",sharedPreferences.getFloat("longitudeLocation",0));
            jsonObject.put("currentStatus",sharedPreferences.getBoolean("currentStatus",false));
            jsonObject.put("verificationStatus",sharedPreferences.getBoolean("verificationStatus",false));

            return jsonObject;
        }catch (JSONException e){
            return null;
        }

    }

    public Boolean setProfile(JSONObject jsonObject){
        try {
            editor.putString("userId",jsonObject.getString("userId"));
            editor.putBoolean("mandatoryData",jsonObject.getBoolean("mandatoryData"));
            editor.putString("enterpriseName",jsonObject.getString("enterpriseName"));
            editor.putString("proprietor",jsonObject.getString("proprietor"));
            editor.putLong("mobileNo",jsonObject.getLong("mobileNo"));
            editor.putFloat("latitudeLocation", BigDecimal.valueOf(jsonObject.getDouble("latitudeLocation")).floatValue());
            editor.putFloat("longitudeLocation", BigDecimal.valueOf(jsonObject.getDouble("longitudeLocation")).floatValue());
            editor.putBoolean("currentStatus",jsonObject.getBoolean("currentStatus"));
            editor.putBoolean("verificationStatus",jsonObject.getBoolean("verificationStatus"));
            editor.commit();
            return true;
        }catch (JSONException e){
            return false;
        }

    }


    String getToken(){
        return sharedPreferences.getString("token",null);
    }

    void setToken(String token){
        editor.putString("Token",token);
        editor.commit();
    }

}

