package com.example.retail.Utils.Common;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mapplicationContext;

    @Inject
    public SharedPreferencesHelper(Context applicationContext) {
        mapplicationContext=applicationContext;
        this.sharedPreferences=applicationContext.getSharedPreferences("myPref",0);
        editor=this.sharedPreferences.edit();
    }



}

