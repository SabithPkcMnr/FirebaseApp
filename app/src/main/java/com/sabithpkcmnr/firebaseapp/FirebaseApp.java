package com.sabithpkcmnr.firebaseapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by SABITH on 2/27/2018.
 */

public class FirebaseApp extends Application {

    @Override
    public void onCreate() {
        Firebase.setAndroidContext(this);
        super.onCreate();
    }
}
