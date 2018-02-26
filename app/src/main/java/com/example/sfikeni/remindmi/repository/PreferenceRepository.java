package com.example.sfikeni.remindmi.repository;


/**
 * Created by SFikeni on 2018/02/22.
 */

public interface PreferenceRepository {

    boolean setReminderHour(int hourOfDay);

    int getReminderHour();

    boolean setReminderMinute(int minute);

    int getReminderMinute();

    boolean getReminderStatus();

    boolean setReminderStatus(boolean status);

    boolean setAlarmId(int alarmId);

    int getAlarmId();

    boolean setNotificationId(int notificationId);

    int getNotificationId();

    boolean resetPreferenceKey(String key);
}
