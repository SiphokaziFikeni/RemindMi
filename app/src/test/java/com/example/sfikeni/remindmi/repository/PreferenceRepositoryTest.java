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
        int minute = sharedPreferenceProvider.getInteger("reminder_minute");

        Assert.assertEquals(12, minute);
        verify(sharedPreferenceProvider).getInteger("reminder_minute");
    }


    @After
    public void tearDown() {

        preferenceRepositoryImpl.resetPreferenceKey("reminder_hour");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("reminder_hour");

        preferenceRepositoryImpl.resetPreferenceKey("reminder_minute");
        verify(sharedPreferenceProvider).removePreferenceValueWithKey("reminder_minute");

        preferenceRepositoryImpl.clearSharedPreferences();
        verify(sharedPreferenceProvider).clearPreferences();
    }

}