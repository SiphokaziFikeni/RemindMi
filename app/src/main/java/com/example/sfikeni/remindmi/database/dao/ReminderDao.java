package com.example.sfikeni.remindmi.database.dao;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.sfikeni.remindmi.Constants;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;
import com.example.sfikeni.remindmi.database.util.LiveRealmData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by SFikeni on 2018/02/05.
 */

public class ReminderDao extends Dao<ReminderEntity>{

    private Realm realm;
    private static ReminderDao sInstance;

    public static ReminderDao ReminderDao() {
        if (sInstance == null){
            sInstance = new ReminderDao();
        }

        return sInstance;
    }

    public void getRealmConfiguration(Context context){
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Constants.REALM_DATABASE_NAME + ".realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()//need to remove this, if migrations are implemented
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public Realm getRealm(){
        this.realm = Realm.getDefaultInstance();
        return this.realm;
    }

    public LiveData<List<ReminderEntity>> getAllReminders(){
        return new LiveRealmData <> (where().findAllAsync());
    }

    private RealmQuery<ReminderEntity> where() {
        return realm.where(ReminderEntity.class);
    }

    public void closeRealmInstance(){
        if (!realm.isClosed()){
            realm.close();
        }
    }

}
