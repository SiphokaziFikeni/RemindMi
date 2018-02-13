package com.example.sfikeni.remindmi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class PreferencesHelper {

    private static final String APP_SHARED_PREFS = "RemindMiPreferences";

    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour = "hour";
    private static final String min = "min";
    private static final String alarm = "alarmId";

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = sharedPreferences.edit();
    }

    public static int getAlarmId(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int alarmId = sharedPreferences.getInt(alarm, 1);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(alarm, alarmId + 1).apply();
        return alarmId;
    }

    // Settings Page Set Reminder

    public boolean getReminderStatus() {
        return sharedPreferences.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status) {
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.commit();
    }

    // Settings Page Reminder Time (Hour)

    public int getHour() {
        return sharedPreferences.getInt(hour, 20);
    }

    public void setHour(int h) {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }

    // Settings Page Reminder Time (Minutes)

    public int getMinute() {
        return sharedPreferences.getInt(min, 0);
    }

    public void setMinute(int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

    public void reset() {
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
