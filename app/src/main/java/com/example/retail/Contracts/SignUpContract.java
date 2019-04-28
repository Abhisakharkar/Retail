package com.example.retail.Contracts;

public interface SignUpContract {
    interface view{
        void showSnackbar(String message,int length);
        void gotoProfileActivity();
        void gotoSignInActivity();
    }

    interface presenter{}
}
