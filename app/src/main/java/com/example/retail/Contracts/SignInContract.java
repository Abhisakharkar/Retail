package com.example.retail.Contracts;

public interface SignInContract {
    interface view{
        void gotoProfileActivity();
        void showSnackbar(String message,int length);
        void gotoSignUpActivity();
    }
    interface presenter{}
}
