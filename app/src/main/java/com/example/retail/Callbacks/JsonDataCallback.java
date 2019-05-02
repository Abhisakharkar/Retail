package com.example.retail.Callbacks;

import org.json.JSONObject;

public interface JsonDataCallback {
    void onSuccess(JSONObject responseData);
    void onFailure(JSONObject errorData);
}


