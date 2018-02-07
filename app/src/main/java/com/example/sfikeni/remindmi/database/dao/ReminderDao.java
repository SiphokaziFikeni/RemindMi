package com.example.sfikeni.remindmi.database.dao;

import android.arch.lifecycle.LiveData;

import com.example.sfikeni.remindmi.database.entity.ReminderEntity;
import com.example.sfikeni.remindmi.database.util.LiveRealmData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by SFikeni on 2018/02/05.
 */

public class ReminderDao extends Dao<ReminderEntity>{

    private Realm realm;

    public ReminderDao(Realm realm) {
        super(realm);
        this.realm = realm;
    }

    public LiveData<List<ReminderEntity>> getAllReminders(){
        return new LiveRealmData <> (where().findAllAsync());
    }

    private RealmQuery<ReminderEntity> where() {
        return realm.where(ReminderEntity.class);
    }

}
