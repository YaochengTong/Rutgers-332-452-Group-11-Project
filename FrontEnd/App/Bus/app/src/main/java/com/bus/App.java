package com.bus;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 */

public class App extends Application {
    public static Firebase firebase;
    public static String ruid;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://android-bus-ac5e3.firebaseio.com/");
    }
}
