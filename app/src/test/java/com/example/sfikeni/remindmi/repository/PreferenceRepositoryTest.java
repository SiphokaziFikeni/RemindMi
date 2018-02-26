package com.example.sfikeni.remindmi.repository;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by SFikeni on 2018/02/21.
 */
public class PreferenceRepositoryTest {

    PreferenceRepositoryImpl preferenceRepositoryImpl;

    @Mock
    SharedPreferenceProvider sharedPreferenceProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        preferenceRepositoryImpl = new PreferenceRepositoryImpl(sharedPreferenceProvider);
    }


    @Test
    public void setReminderHour() {
        int hour = 10;
        preferenceRepositoryImpl.setReminderHour(hour);
        verify(sharedPreferenceProvider).writeInteger("reminder_hour", hour);
    }

    @Test
    public void getReminderHour() {
        when(sharedPreferenceProvider.getInteger("reminder_hour")).thenReturn(5);
        int hour = preferenceRepositoryImpl.getReminderHour();

        Assert.assertEquals(5, hour);
        verify(sharedPreferenceProvider).getInteger("reminder_hour");
    }

    @Test
    public void setReminderMinute() {
        int minute = 5;
        preferenceRepositoryImpl.setReminderMinute(minute);
        verify(sharedPreferenceProvider).writeInteger("reminder_minute", minute);
    }

    @Test
    public void getReminderMinute() {
        when(sharedPreferenceProvider.getInteger("reminder_minute")).thenReturn(12);
        int minute = preferenceRepositoryImpl.getReminderMinute();

        Assert.assertEquals(12, minute);
        verify(sharedPreferenceProvider).getInteger("reminder_minute");
    }

    @Test
    public void setReminderStatusTrue() {
        preferenceRepositoryImpl.setReminderStatus(true);
        verify(sharedPreferenceProvider).writeBoolean("reminder_status", true);
    }

    @Test
    public void setReminderStatusFalse() {
        preferenceRepositoryImpl.setReminderStatus(false);
        verify(sharedPreferenceProvider).writeBoolean("reminder_status", false);
    }

    @Test
    public void getReminderStatusFalse() {
        when(sharedPreferenceProvider.getBoolean("reminder_status")).thenReturn(false);
        boolean status = preferenceRepositoryImpl.getReminderStatus();

        Assert.assertEquals(false, status);
        verify(sharedPreferenceProvider).getBoolean("reminder_status");
    }

    @Test
    public void getReminderStatusTrue() {
        when(sharedPreferenceProvider.getBoolean("reminder_status")).thenReturn(true);
        boolean status = preferenceRepositoryImpl.getReminderStatus();

        Assert.assertEquals(true, status);
        verify(sharedPreferenceProvider).getBoolean("reminder_status");
    }

    @Test
    public void setAlarmId() {
        int alarmId = 2;
        preferenceRepositoryImpl.setAlarmId(alarmId);
        verify(sharedPreferenceProvider).writeInteger("alarm_id", alarmId);
    }

    @Test
    public void getAlarmId() {
        when(sharedPreferenceProvider.getInteger("alarm_id")).thenReturn(2);
        int alarmId = preferenceRepositoryImpl.getAlarmId();

        Assert.assertEquals(2, alarmId);
        verify(sharedPreferenceProvider).getInteger("alarm_id");
    }

    @Test
    public void setNotificationId() {
        int notificationId = 23;
        preferenceRepositoryImpl.setNotificationId(notificationId);
        verify(sharedPreferenceProvider).writeInteger("notification_id", notificationId);
    }

    @Test
    public void getNotificationId() {
        when(sharedPreferenceProvider.getInteger("notification_id")).thenReturn(23);
        int notificationId = preferenceRepositoryImpl.getNotificationId();

        Assert.assertEquals(23, notificationId);
        verify(sharedPreferenceProvider).getInteger("notification_id");
    }

    @After
    public void tearDown() {

        preferenceRepositoryImpl.resetPreferenceKey("reminder_hour");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("reminder_hour");

        preferenceRepositoryImpl.resetPreferenceKey("reminder_minute");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("reminder_minute");

        preferenceRepositoryImpl.resetPreferenceKey("reminder_status");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("reminder_status");

        preferenceRepositoryImpl.resetPreferenceKey("alarm_id");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("alarm_id");

        preferenceRepositoryImpl.resetPreferenceKey("notification_id");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("notification_id");

        preferenceRepositoryImpl.clearSharedPreferences();
        verify(sharedPreferenceProvider).clearPreferences();
    }

}