package com.example.retail.Utils.Common;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mapplicationContext;

    @Inject
    public SharedPreferencesHelper(@Named("applicationContext") Context applicationContext) {
        mapplicationContext=applicationContext;
        this.sharedPreferences=applicationContext.getSharedPreferences("myPref",0);
        editor=this.sharedPreferences.edit();
    }

    public int getisMandatoryDataFilled(){
        return sharedPreferences.getInt("mandatoryData",-1);
    }

    String getToken(){
        return sharedPreferences.getString("token",null);
    }

    void setToken(String token){
        editor.putString("Token",token);
        editor.commit();
    }

}

