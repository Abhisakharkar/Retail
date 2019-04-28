package com.example.retail;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.retail.Contracts.SignUpContract;
import com.example.retail.Enum.CredentialEnum;
import com.example.retail.Utils.Common.CredentialValidator;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.view {


    private ConstraintLayout parentLayout;
    private EditText mailEdittext, passEdittext, confirmPassEdittext;
    private CredentialValidator credentialValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        parentLayout = findViewById(R.id.signupactivity_parent_layout);
        mailEdittext = findViewById(R.id.signupactivity_mail_edittext);
        passEdittext = findViewById(R.id.signupactivity_pass_edittext);
        confirmPassEdittext = findViewById(R.id.signupactivity_confirm_pass_edittext);
        credentialValidator=new CredentialValidator();
    }

    public void SignUpButtonOnClick(View view) {
         CredentialEnum result=credentialValidator.ValidateForSignUp(mailEdittext.getText(),passEdittext.getText(),confirmPassEdittext.getText());
         if (result==CredentialEnum.OK){

         }else {
             showSnackbar(result.toString(),Snackbar.LENGTH_SHORT);
         }
    }

    public void gotoSignInButtonOnClick(View view){
        gotoSignInActivity();
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
    public void gotoProfileActivity() {
        Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void gotoSignInActivity() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
