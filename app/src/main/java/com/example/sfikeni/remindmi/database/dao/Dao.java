package com.example.sfikeni.remindmi.database.dao;

import io.realm.Realm;
import io.realm.RealmModel;

/**
 * Created by SFikeni on 2018/02/07.
 */

public abstract class Dao <T extends RealmModel> {

    private Realm realm;

    Dao(){
    }

    public Dao(Realm realm) {
        this.realm = realm;
    }

    public T copyOrUpdate(T entity) {

        if(realm.isInTransaction()) {
            entity = realm.copyToRealmOrUpdate(entity);

        } else {
            try {
                realm.beginTransaction();
                entity = realm.copyToRealmOrUpdate(entity);
                realm.commitTransaction();

            } catch (Exception e) {
                realm.cancelTransaction();
                throw e;
            }
        }

        return entity;
    }
}
