package com.example.sfikeni.remindmi.database.util;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.annotation.Nonnull;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by SFikeni on 2018/02/07.
 */

public class LiveRealmData<T extends RealmModel> extends LiveData<List<T>> {

    private RealmResults<T> realmResults;
    private RealmChangeListener<RealmResults<T>> listener;

    public LiveRealmData(RealmResults<T> realmResults) {
        this.realmResults = realmResults;

        listener = new RealmChangeListener<RealmResults<T>>() {
                    @Override
                    public void onChange(@Nonnull RealmResults<T> results) {
                        setValue(results);
                    }
         };
    }

    @Override
    protected void onActive() {
        super.onActive();
        realmResults.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        realmResults.removeChangeListener(listener);
    }

}
