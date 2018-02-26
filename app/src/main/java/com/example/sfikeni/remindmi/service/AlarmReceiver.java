package com.example.sfikeni.remindmi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sfikeni.remindmi.Constants;
import com.example.sfikeni.remindmi.repository.PreferenceRepositoryImpl;
import com.example.sfikeni.remindmi.repository.SharedPreferenceProvider;
import com.example.sfikeni.remindmi.ui.reminderdetails.ReminderDetailsActivity;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationScheduler notificationScheduler = new NotificationScheduler(new PreferenceRepositoryImpl(new SharedPreferenceProvider(context)));

        if (intent.getAction() != null && context != null) {

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                PreferenceRepositoryImpl preferenceRepositoryImpl = new PreferenceRepositoryImpl(new SharedPreferenceProvider(context));
                notificationScheduler.setReminder(context, AlarmReceiver.class, intent.getStringExtra(Constants.REMINDER_ID_TAG)
                        , intent.getStringExtra(Constants.REMINDER_TITLE_TAG)
                        , intent.getStringExtra(Constants.REMINDER_DESCRIPTION_TAG)
                        , preferenceRepositoryImpl.getReminderHour(), preferenceRepositoryImpl.getReminderMinute());
                return;
            }
        }

        notificationScheduler.showNotification(context, ReminderDetailsActivity.class, intent.getStringExtra(Constants.REMINDER_ID_TAG), intent.getStringExtra(Constants.REMINDER_TITLE_TAG), intent.getStringExtra(Constants.REMINDER_DESCRIPTION_TAG));
    }

}
