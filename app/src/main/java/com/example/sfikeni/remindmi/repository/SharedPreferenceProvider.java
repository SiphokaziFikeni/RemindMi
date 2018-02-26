package com.example.sfikeni.remindmi.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sfikeni.remindmi.Constants;

/**
 * Created by SFikeni on 2018/02/21.
 */

public class SharedPreferenceProvider {

    SharedPreferences sharedPreferences;
    Context context;

    public SharedPreferenceProvider(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.APP_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public boolean writeInteger(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value);
        return prefsEditor.commit();
    }

    public Integer getInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean writeBoolean(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        return prefsEditor.commit();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean writeString(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        return prefsEditor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public boolean removePreferenceValueWithKey(String key) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(key);
        return prefsEditor.commit();
    }

    public boolean clearPreferences(){
       return sharedPreferences.edit().clear().commit();
    }
}
