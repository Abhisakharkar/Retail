package com.example.retail;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.retail.Contracts.ProfileContract;
import com.example.retail.Presenter.ProfilePresenter;
import com.example.retail.di.Components.AppComponent;
import com.example.retail.di.RetailApp;

public class ProfileActivity extends AppCompatActivity implements ProfileContract.view {
    private EditText proprietorEdittext, mobileNoEdittext, shopNameEdittext, addressLine1Edittext, licenseNoEdittext;
    private TextInputLayout proprietorLayout, mobileNoLayout, shopNameLayout, addressLine1Layout;
    private ImageView lpImageView;
    private Button myLocationBtn;
    private RelativeLayout parentLayout;
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        shopNameEdittext=findViewById(R.id.profile_activity_shop_name_edittext_id);
        proprietorEdittext=findViewById(R.id.profile_activity_proprietor_edittext_id);
        mobileNoEdittext=findViewById(R.id.profile_activity_mobile_no_edittext_id);
        myLocationBtn=findViewById(R.id.profile_activity_location_btn_id);
        addressLine1Edittext=findViewById(R.id.profile_activity_addline_1_edittext_id);
        lpImageView=findViewById(R.id.profile_activity_license_photo_imageview_id);
        parentLayout=findViewById(R.id.parent_profile);
        AppComponent appComponent=((RetailApp) getApplication()).getAppComponent();
        presenter=new ProfilePresenter(this,appComponent.getSharedPreferencesHelper(),appComponent.getVolleyHelper());
    }

    public void onSaveProfileBtn(View view){
        presenter.sendProfile(shopNameEdittext.getText().toString().trim(),proprietorEdittext.getText().toString().trim(),mobileNoEdittext.getText().toString().trim());
    }

    @Override
    public void showSnackbar(String message, int length) {
        final Snackbar snackbar = Snackbar.make(parentLayout, message, length);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public void gotoHomeActivity() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void goBackToSignInAndClearData() {
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            ((ActivityManager)this.getSystemService(ACTIVITY_SERVICE))
                    .clearApplicationUserData(); // note: it has a return value!
        } else {
           showSnackbar("unable to clear data",Snackbar.LENGTH_SHORT);
        }
        Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
