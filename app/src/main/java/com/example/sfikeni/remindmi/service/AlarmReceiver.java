package com.example.sfikeni.remindmi.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sfikeni.remindmi.Constants;
import com.example.sfikeni.remindmi.ui.reminderdetails.ReminderDetailsActivity;
import com.example.sfikeni.remindmi.utils.PreferencesHelper;

/**
 * Created by SFikeni on 2018/02/13.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                PreferencesHelper preferencesHelper = new PreferencesHelper(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, intent.getStringExtra(Constants.REMINDER_ID_TAG)
                        , intent.getStringExtra(Constants.REMINDER_TITLE_TAG), intent.getStringExtra(Constants.REMINDER_DESCRIPTION_TAG), preferencesHelper.getHour(), preferencesHelper.getMinute());
                return;
            }
        }

        NotificationScheduler.showNotification(context, ReminderDetailsActivity.class, intent.getStringExtra(Constants.REMINDER_ID_TAG), intent.getStringExtra(Constants.REMINDER_TITLE_TAG), intent.getStringExtra(Constants.REMINDER_DESCRIPTION_TAG));
    }

}
