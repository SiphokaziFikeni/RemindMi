package com.example.sfikeni.remindmi.app;

import android.app.Application;

/**
 * Created by SFikeni on 2018/02/02.
 */

public class RemindMiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Realm.init(this);

//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name(Constants.REALM_DATABASE_NAME + ".realm")
//                .schemaVersion(1)
//                .deleteRealmIfMigrationNeeded()//need to remove this, if migrations are implemented
//                .build();
//
//        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
