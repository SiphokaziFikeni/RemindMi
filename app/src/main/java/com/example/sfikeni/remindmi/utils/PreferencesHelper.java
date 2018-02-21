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
    private Context context;

    public PreferencesHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    private static void setAlarmId(int newAlarmId){
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(alarm, newAlarmId + 1).apply();
    }

    public static int getAlarmId(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        int alarmId = sharedPreferences.getInt(alarm, 1);

        setAlarmId(alarmId + 1);
//        prefsEditor = sharedPreferences.edit();
//        prefsEditor.putInt(alarm, alarmId + 1).apply();
        return alarmId;
    }

    private static void setNotificationId(int newNotificationId){
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(notification, newNotificationId).apply();
    }

    public static int getNotificationId(Context context){
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        int notificationId = sharedPreferences.getInt(notification, 1);
        setNotificationId(notificationId + 1);
//        prefsEditor = sharedPreferences.edit();
//        prefsEditor.putInt(notification, notificationId + 1).apply();
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

    public boolean setHour(int hourOfDay) {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(hour, hourOfDay);

        return prefsEditor.commit();
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
