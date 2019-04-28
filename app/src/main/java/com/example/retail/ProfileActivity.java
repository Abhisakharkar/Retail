package com.example.retail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.retail.Contracts.ProfileContract;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

}
