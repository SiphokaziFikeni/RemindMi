package com.example.sfikeni.remindmi.repository;

import com.example.sfikeni.remindmi.Constants;

/**
 * Created by SFikeni on 2018/02/21.
 */

public class PreferenceRepositoryImpl implements PreferenceRepository {

    private final SharedPreferenceProvider sharedPreferenceProvider;

    public PreferenceRepositoryImpl(SharedPreferenceProvider sharedPreferenceProvider) {
        this.sharedPreferenceProvider = sharedPreferenceProvider;
    }

    @Override
    public boolean setReminderHour(int hourOfDay) {
        return sharedPreferenceProvider.writeInteger(Constants.REMINDER_HOUR, hourOfDay);
    }

    @Override
    public int getReminderHour() {
        return sharedPreferenceProvider.getInteger(Constants.REMINDER_HOUR);
    }

    @Override
    public boolean setReminderMinute(int minute) {
        return sharedPreferenceProvider.writeInteger(Constants.REMINDER_MINUTE, minute);
    }

    @Override
    public int getReminderMinute() {
        return sharedPreferenceProvider.getInteger(Constants.REMINDER_MINUTE);
    }

    @Override
    public boolean setReminderStatus(boolean status) {
        return sharedPreferenceProvider.writeBoolean(Constants.REMINDER_STATUS, status);
    }

    @Override
    public boolean getReminderStatus() {
        return sharedPreferenceProvider.getBoolean(Constants.REMINDER_STATUS);
    }

    @Override
    public boolean setAlarmId(int alarmId) {
        return sharedPreferenceProvider.writeInteger(Constants.REMINDER_ALARM_ID, alarmId);
    }

    @Override
    public int getAlarmId() {
        return sharedPreferenceProvider.getInteger(Constants.REMINDER_ALARM_ID);
    }

    @Override
    public boolean setNotificationId(int notificationId) {
        return sharedPreferenceProvider.writeInteger(Constants.NOTIFICATION_ID, notificationId);
    }

    @Override
    public int getNotificationId() {
        return sharedPreferenceProvider.getInteger(Constants.NOTIFICATION_ID);
    }

    @Override
    public boolean resetPreferenceKey(String key) {
        return sharedPreferenceProvider.removePreferenceValueWithKey(key);
    }

    @Override
    public boolean clearSharedPreferences() {
        return sharedPreferenceProvider.clearPreferences();
    }

}
