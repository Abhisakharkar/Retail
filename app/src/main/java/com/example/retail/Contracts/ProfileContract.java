package com.example.retail.Contracts;

public interface ProfileContract {
    interface view{
        void showSnackbar(String message,int length);

        void gotoHomeActivity();

        void goBackToSignInAndClearData();
    }
    interface presenter{}
}
