package com.example.sfikeni.remindmi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class PreferencesHelper {

    private static final String APP_SHARED_PREFS = "RemindMiPreferences";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour = "hour";
    private static final String min = "min";
    private static final String alarm = "alarmId";
    private static final String notification = "notification";

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static int getAlarmId(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        int alarmId = sharedPreferences.getInt(alarm, 1);

        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(alarm, alarmId + 1).apply();
        return alarmId;
    }

    public static int getNotificationId(Context context){
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        int notificationId = sharedPreferences.getInt(notification, 1);

        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(notification, notificationId + 1).apply();
        return notificationId;
    }

    public boolean getReminderStatus() {
        return sharedPreferences.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status) {
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(reminderStatus, status).apply();
    }

    public int getHour() {
        return sharedPreferences.getInt(hour, 20);
    }

    public void setHour(int hourOfDay) {
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(hour, hourOfDay).apply();
    }

    public int getMinute() {
        return sharedPreferences.getInt(min, 0);
    }

    public void setMinute(int minute) {
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(min, minute).apply();
    }

    public void reset() {
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
