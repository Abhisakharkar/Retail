package com.example.retail.Utils.Common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.retail.Callbacks.JsonDataCallback;
import com.example.retail.Callbacks.SuccessFailureCallback;
import com.example.retail.R;
import com.example.retail.Utils.Common.SharedPreferencesHelper;
import com.example.retail.di.RetailApp;


import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class VolleyHelper {

    private String TAG = "VolleyHelper";
//    private static final String BaseUrl="https://ec2-13-234-45-216.ap-south-1.compute.amazonaws.com";
    private static final String BaseUrl="https://192.168.1.100";
    @Inject
    RetailApp context;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private RequestQueue requestQueue;
    @Inject
    public VolleyHelper(SharedPreferencesHelper sharedPreferencesHelper) {
        requestQueue = Volley.newRequestQueue(context, new HurlStack(null, getSocketFactory()));
        this.sharedPreferencesHelper=sharedPreferencesHelper;
    }

//    public void sendFirebaseToken(String token){
//        String path = "/testWholesaleToken";
//        try{
//            JSONObject headers = new JSONObject();
//            headers.put("Authorization",token);
//            headers.put("Content-Type","application/json");
//
//            JSONObject body = new JSONObject();
//            body.put("Authorization",token);
//
//            sendRequest2("POST", path, headers, body);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public void sendRequest(String path, JSONObject params, final JSONObject requestBody, JsonDataCallback callback) {

        String URL = BaseUrl + path;
        int method = 0;

        Log.d(TAG, "sendRequest: headers ::: "+params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, URL, params
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response);
                Log.d(TAG, "onResponse: Response : "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: Error : "+error.getMessage());
                callback.onFailure(null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("Content-Type","application/json");
                headerMap.put("Authorization", sharedPreferencesHelper.getToken());
                return headerMap;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    private SSLSocketFactory getSocketFactory() {

        CertificateFactory cf = null;
        Certificate ca=null;
        try {
            cf = CertificateFactory.getInstance("X.509");
            InputStream inputStream = context.getResources().openRawResource(R.raw.myrootca);
            ca = cf.generateCertificate(inputStream);

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);


            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);


            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {

                    Log.e("CipherUsed", session.getCipherSuite());
                    return hostname.compareTo("ubuntu") == 0; //The Hostname of your server

                }
            };

            HostnameVerifier hostnameVerifierTEMP = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };


            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifierTEMP);
            SSLContext context = null;
            context = SSLContext.getInstance("TLS");

            context.init(null, tmf.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

            SSLSocketFactory sf = context.getSocketFactory();


            return sf;

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return null;
    }

}
