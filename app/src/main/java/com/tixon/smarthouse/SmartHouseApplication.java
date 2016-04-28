package com.tixon.smarthouse;

import android.app.Application;

import com.tixon.smarthouse.database.HelperFactory;

/**
 * Created by tikhon.osipov on 28.04.2016
 */
public class SmartHouseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
