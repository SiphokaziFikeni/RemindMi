package com.example.sfikeni.remindmi.database.dao;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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

    private RealmQuery<ReminderEntity> where() {
        return realm.where(ReminderEntity.class);
    }

    public LiveData<List<ReminderEntity>> getAllReminders(){
        return new LiveRealmData <> (where().findAllAsync());
    }

    public void saveReminder(final ReminderEntity reminderEntity){

        if (realm == null){
            getRealm();
        }

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm bgRealm) {
                ReminderEntity reminder = bgRealm.createObject(ReminderEntity.class, reminderEntity.getId());

                reminder.setTitle(reminderEntity.getTitle());
                reminder.setDescription(reminderEntity.getDescription());
                reminder.setPriorityLevel(reminderEntity.getPriorityLevel());
                reminder.setDateString(reminderEntity.getDateString());
                reminder.setTimeString(reminderEntity.getTimeString());
                reminder.setStatus(reminderEntity.getStatus());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("Realm write", "successful");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                Log.d("Realm write", "failed");
            }
        });

    }

    public ReminderEntity getReminderById(String id){
        return where().equalTo("id", id).findFirst();
    }

    public void closeRealmInstance(){
        if (!realm.isClosed()){
            realm.close();
        }
    }

}
