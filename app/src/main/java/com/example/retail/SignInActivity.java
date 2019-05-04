package com.example.retail;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.retail.Components.AppComponent;
import com.example.retail.Contracts.SignInContract;
import com.example.retail.Enum.CredentialEnum;
import com.example.retail.Presenter.SignInPresenter;
import com.example.retail.Utils.Common.CredentialValidator;

import javax.inject.Inject;

public class SignInActivity extends AppCompatActivity implements SignInContract.view {

    private EditText mailEdittext, passEdittext;
    private SignInPresenter presenter;
    private ConstraintLayout parentLayout;

    private static final String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        parentLayout = findViewById(R.id.signinactivity_parent_layout);
        mailEdittext = findViewById(R.id.signinactivity_mail_edittext);
        passEdittext = findViewById(R.id.signinactivity_pass_edittext);
        AppComponent appComponent=((RetailApp) getApplication()).getAppComponent();

        presenter=new SignInPresenter(this,appComponent.getDataManager(),appComponent.getCredentialValidator(),appComponent.getFirebaseHelper());
    }
    public void SignInButtonOnClick(View view) {
        presenter.signIn(mailEdittext.getText().toString().trim(),passEdittext.getText().toString().trim());
    }

    public void gotoSignUpButtonOnClick(View view){
        gotoSignUpActivity();
    }

    @Override
    public void gotoProfileActivity() {
        Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
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
    public void gotoSignUpActivity() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void gotoHomeActivity() {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }


}
