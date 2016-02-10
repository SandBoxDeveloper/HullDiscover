package com.hulldiscover.zeus.hulldiscover;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Zeus on 19/11/15.
 */
public class DiscoverApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "pcsscTFNNy2FVxkkQax3GHC5dAgFQdNDkENlyYtg", "j1f4kYbd4bubAhoddNoT7Pl8pGdgOIByUtCdjPTb");


    }

}
