package com.example.waiterapp.helpers;

public class EmailVerify {

    public static boolean isEmailValid(CharSequence email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
